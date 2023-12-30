package foliocontrol.android.foliocontrolandroid.ui.viewModels

import android.content.Context
import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import foliocontrol.android.foliocontrolandroid.data.document.Downloader
import foliocontrol.android.foliocontrolandroid.data.local.auth.TokenRepo
import foliocontrol.android.foliocontrolandroid.data.local.database.PartnershipDatabase
import foliocontrol.android.foliocontrolandroid.data.local.database.PropertyDatabase

import foliocontrol.android.foliocontrolandroid.data.local.schema.asDomainModel
import foliocontrol.android.foliocontrolandroid.data.repository.AuthService
import foliocontrol.android.foliocontrolandroid.data.repository.AuthServiceImpl
import foliocontrol.android.foliocontrolandroid.data.repository.PropertyService
import foliocontrol.android.foliocontrolandroid.data.repository.PropertyServiceImpl
import foliocontrol.android.foliocontrolandroid.domain.Partnership
import foliocontrol.android.foliocontrolandroid.domain.Premise
import foliocontrol.android.foliocontrolandroid.domain.Property
import foliocontrol.android.foliocontrolandroid.domain.PropertyDocument
import foliocontrol.android.foliocontrolandroid.domain.asPartnershipRoomEntity
import foliocontrol.android.foliocontrolandroid.domain.asPropertyRoomEntity
import foliocontrol.android.foliocontrolandroid.ui.viewModels.common.UiState
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File
import java.io.IOException

/**
 * ViewModel responsible for managing the data and logic related to the properties in the application.
 *
 * @property propertyRepo The local database representing the property repository.
 * @property partnershipRepo The local database representing the partnership repository.
 * @property downloader An instance of [Downloader] for handling file downloads.
 */
class PropertyViewModel(
    private val propertyRepo: PropertyDatabase,
    private val partnershipRepo: PartnershipDatabase,
    private val tokenRepo: TokenRepo,
    private val downloader: Downloader,
) : ViewModel() {
    var uiState: UiState by mutableStateOf(
        UiState.LoggedOut("You are not logged in"),
    )
        private set

    var authService: AuthService = AuthServiceImpl()
    var propertyService: PropertyService = PropertyServiceImpl()
    var currentPartnership by mutableStateOf(Partnership())
        private set

    var propertyDocuments by mutableStateOf(emptyList<PropertyDocument>())
        private set

    var propertyDocument by mutableStateOf(PropertyDocument())
        private set
    var propertyPremises by mutableStateOf(emptyList<Premise>())
        private set

    var partnershipListState by mutableStateOf(emptyList<Partnership>())
        private set
    var propertyListState by mutableStateOf(emptyList<Property>())
        private set

    var filteredList by mutableStateOf(emptyList<Property>())
        private set
    var propertyState by mutableStateOf(Property())
        private set

    var propertyImageState by mutableStateOf(
        MultipartBody.Part.createFormData(
            "propertyImage",
            "null",
        ),
    )
        private set

    var propertyDocumentState by mutableStateOf(
        MultipartBody.Part.createFormData(
            "document",
            "null",
        ),
    )
        private set
    var isAddPropertyDialogOpen by mutableStateOf(false)
    var isSearchBarEnabled by mutableStateOf(false)
    var addPropertyState by mutableStateOf(Property())
        private set


    fun getToken(): String {
        return tokenRepo.getToken()
    }

    fun getData() {
        uiState = UiState.Loading
        viewModelScope.launch {
            try {
                getPartnershipListForLoggedInUser()
                if (partnershipListState.isNotEmpty() && currentPartnership.partnershipID == 0) {
                    defaultPartnership()
                }
                getPropertyListForPartnership()
                if (propertyListState.isNotEmpty()) {
                    uiState = UiState.Success("Succesfully retrieved data")
                } else {
                    uiState = UiState.Error("No data found")
                }
                println(uiState)
            } catch (e: IOException) {
                val partnershipList =
                    partnershipRepo.getPartnerships().first().map { it.asDomainModel() }
                if (partnershipList.isNotEmpty() && currentPartnership.partnershipID == 0) {
                    partnershipListState = partnershipList
                    defaultPartnership()
                }
                val propertyList =
                    propertyRepo.getPropertiesByActivePartnership(currentPartnership).first()
                        .map { it.asDomainModel() }
                if (propertyList.isNotEmpty()) {
                    propertyListState = propertyList
                    uiState = UiState.Offline("No network detected, previewing offline data")
                } else {
                    uiState = UiState.Error("No data found")
                }
            } catch (e: Exception) {
                uiState = UiState.Error("Something went very wrong: ${e.message}")
            }
        }
    }

    /**
     * Filters the list of properties based on the provided query.
     *
     * @param query The search query to filter the property list.
     */
    fun filterProperties(query: String) {
        filteredList = propertyListState.filter {
            it.propertyName.contains(query, ignoreCase = true) || it.propertyType.contains(
                query, ignoreCase = true,
            ) || it.country.contains(query, ignoreCase = true)
        }
    }

    /**
     * Selects a specific property, updating the [propertyState] and clearing associated data.
     *
     * @param property The property to be selected.
     */
    fun selectProperty(property: Property) {
        propertyState = property
        propertyDocuments = emptyList()
        propertyPremises = emptyList()
    }

    /**
     * Retrieves the list of properties for the active partnership.
     * If online, the list is fetched from the server and stored in the local database.
     */
    suspend fun getPropertyListForPartnership() {
        try {
            propertyListState = propertyService.getProperties(
                getToken(), currentPartnership,
            )
            if (propertyListState.isNotEmpty()) {
                propertyRepo.dropTable(currentPartnership.partnershipID)
            }
            propertyRepo.insertAll(propertyListState.map { it.asPropertyRoomEntity() })
        } catch (e: Exception) {
            println("Error: ${e.message}")
        } finally {

        }
    }

    /**
     * Retrieves the list of partnerships for the currently logged-in user.
     * If online, the list is fetched from the server; otherwise, it retrieves the data from the local database.
     */
    suspend fun getPartnershipListForLoggedInUser() {
        partnershipListState =
            authService.getPartnershipsForLoggedInUser(getToken())
        partnershipRepo.insertAllPartnerships(
            partnershipListState.map {
                it.asPartnershipRoomEntity()
            },
        )
    }

    /**
     * Downloads a file from the provided URL using [downloader].
     *
     * @param url The URL of the file to be downloaded.
     * @return The download ID for tracking the download progress.
     */
    fun downloadFile(url: String): Long {
        return downloader.downloadFile(url)
    }


    /**
     * Switches the active partnership, updating the [propertyListState] and [currentPartnership].
     *
     * @param partnership The partnership to switch to.
     */
    fun switchPartnership(partnership: Partnership) {
        propertyListState = emptyList()
        currentPartnership = partnership
    }

    fun defaultPartnership() {
        currentPartnership = partnershipListState[0]
    }

    /**
     * Retrieves property details, premises, and documents for the active property.
     * If online, it fetches the data from the server; otherwise, it previews the offline data.
     */
    fun getDataForActiveProperty() {
        viewModelScope.launch {
            try {
                getDetailsForProperty(propertyState)
                getPremisesForProperty(propertyState)
                getDocumentsForProperty(propertyState)
                uiState = UiState.Success("Succesfully retrieved data")
            } catch (e: IOException) {
                // TODO: implement offline data
                uiState = UiState.Offline("No network detected")
            } catch (e: Exception) {
                uiState = UiState.Error("Something went very wrong: ${e.message}")
            }
        }
    }

    /**
     * Uploads an image for the active property.
     *
     * @param context The context to retrieve the content resolver.
     * @param uri The URI of the image to be uploaded.
     */
    fun uploadImage(
        context: Context,
        uri: Uri,
    ) {
        val file = uriToFile(context, uri)
        val requestFile = file?.asRequestBody("image/*".toMediaTypeOrNull())

        if (file != null && requestFile != null) {
            propertyImageState =
                MultipartBody.Part.createFormData("propertyImage", file.name, requestFile)
        } else {
            // Handle the case when the conversion fails
        }
        handlePropertySave()
    }

    fun handleDocumentUpload() {
        viewModelScope.launch {
            try {
                propertyService.uploadDocument(
                    getToken(),
                    propertyDocumentState,
                    propertyDocument,
                )
                getDataForActiveProperty()
            } catch (e: Exception) {
                println("Error: ${e.message}")
            } finally {
                println("Finally")
            }
        }
    }

    /**
     * Uploads a document for the active property.
     *
     * @param context The context to retrieve the content resolver.
     * @param uri The URI of the document to be uploaded.
     */
    fun uploadDocument(
        context: Context,
        uri: Uri,
    ) {
        val file = uriToFile(context, uri)
        val name = file?.name
        val requestFile = file?.asRequestBody("pdf/*".toMediaTypeOrNull())

        if (file != null && requestFile != null) {
            propertyDocumentState =
                MultipartBody.Part.createFormData("document", file.name, requestFile)
        } else {
            // Handle the case when the conversion fails
        }

        handleAddDocumentEdit(
            FK_propertyID = propertyState.propertyID,
            name = name,
        )
        handleDocumentUpload()
        clearDocument()
    }

    private fun uriToFile(
        context: Context,
        uri: Uri,
    ): File? {
        val inputStream = context.contentResolver.openInputStream(uri)
        val tempFile = kotlin.io.path.createTempFile()

        inputStream?.use { input ->
            tempFile.toFile().outputStream().use { output ->
                input.copyTo(output)
            }
        }

        return tempFile.toFile()
    }

    suspend fun getPremisesForProperty(property: Property) {
        propertyPremises = propertyService.getPremisesForProperty(
            getToken(), property,
        )
    }

    suspend fun getDetailsForProperty(property: Property) {
        propertyState = propertyService.getDetailsForProperty(
            getToken(), property,
        )
    }

    suspend fun getDocumentsForProperty(property: Property) {
        propertyDocuments = propertyService.getDocumentsForProperty(
            getToken(), property,
        )
    }

    fun handlePropertyEdit(
        propertyName: String? = null,
        propertyType: String? = null,
        propertyImg: String? = null,
        street: String? = null,
        streetNumber: String? = null,
        city: String? = null,
        zipCode: String? = null,
        country: String? = null,
    ) {
        propertyName?.let {
            propertyState = propertyState.copy(propertyName = it)
        }
        propertyType?.let {
            propertyState = propertyState.copy(propertyType = it)
        }
        propertyImg?.let {
            propertyState = propertyState.copy(propertyImg = it)
        }
        street?.let {
            propertyState = propertyState.copy(street = it)
        }
        streetNumber?.let {
            propertyState = propertyState.copy(streetNumber = it)
        }
        city?.let {
            propertyState = propertyState.copy(city = it)
        }
        zipCode?.let {
            propertyState = propertyState.copy(zipCode = it)
        }
        country?.let {
            propertyState = propertyState.copy(country = it)
        }
    }

    fun handleAddDocumentEdit(
        name: String? = null,
        documentType: String? = null,
        expiryDate: String? = null,
        FK_propertyID: Int? = null,
    ) {
        name?.let {
            propertyDocument = propertyDocument.copy(name = it)
        }
        documentType?.let {
            propertyDocument = propertyDocument.copy(documentType = it)
        }
        FK_propertyID?.let {
            propertyDocument = propertyDocument.copy(propertyId = it)
        }
        expiryDate?.let {
            propertyDocument = propertyDocument.copy(expiryDate = it)
        }
    }

    /**
     * Clears the selected image and resets [propertyImageState].
     */
    fun clearImage() {
        handlePropertyEdit(
            propertyImg = "null",
        )
        propertyImageState = MultipartBody.Part.createFormData(
            "propertyImage", "null",
        )

        handlePropertySave()
    }

    /**
     * Clears the selected document and resets [propertyDocumentState].
     */
    fun clearDocument() {
        propertyDocumentState = MultipartBody.Part.createFormData(
            "document", "null",
        )
        handleAddDocumentEdit(
            name = " ",
            documentType = " ",
            expiryDate = " ",
            FK_propertyID = null,
        )
    }

    /**
     * Handles the save operation for the active property, including updating property details and uploading the image.
     */
    fun handlePropertySave() {
        viewModelScope.launch {
            try {
                propertyService.savePropertyByPropertyID(
                    getToken(),
                    propertyState,
                    propertyImageState,
                )
                getDataForActiveProperty()
            } catch (e: Exception) {
                println("Error: ${e.message}")
            } finally {
                println("Finally")
            }
        }
    }

    /**
     * Handles the deletion of a property with the given [propertyId].
     *
     * @param propertyId The ID of the property to be deleted.
     */
    fun handlePropertyDelete(propertyId: Int) {
        viewModelScope.launch {
            try {
                propertyService.deletePropertyByPropertyID(
                    getToken(),
                    propertyId,
                )
                getData()
                // pop from list and delete in db
            } catch (e: Exception) {
                println("Error: ${e.message}")
            } finally {
                println("Finally")
            }
        }
    }

    /**
     * Handles the deletion of a document with the given [selectedDocumentID].
     *
     * @param selectedDocumentID The ID of the document to be deleted.
     */
    fun handleDocumentDelete(selectedDocumentID: Int) {
        viewModelScope.launch {
            try {
                propertyService.deleteDocumentByPropertyID(
                    getToken(),
                    selectedDocumentID,
                )
                getDataForActiveProperty()
                // pop from list and delete in db
            } catch (e: Exception) {
                println("Error: ${e.message}")
            } finally {
                println("Finally")
            }
        }
    }

    /**
     * Toggles the visibility of the property add dialog.
     */
    fun togglePropertyAddDialog() {
        isAddPropertyDialogOpen = !isAddPropertyDialogOpen
    }

    /**
     * Toggles the visibility of the search bar.
     */
    fun toggleSearchBar() {
        isSearchBarEnabled = !isSearchBarEnabled
        filteredList = propertyListState
    }

    /**
     * Handles the property addition or editing, updating [addPropertyState] accordingly.
     *
     * @param propertyName The name of the property.
     * @param propertyType The type of the property.
     * @param street The street address of the property.
     * @param streetNumber The street number of the property.
     * @param city The city of the property.
     * @param zipCode The zip code of the property.
     * @param country The country of the property.
     */
    fun handlePropertyAddEdit(
        propertyName: String? = null,
        propertyType: String? = null,
        street: String? = null,
        streetNumber: String? = null,
        city: String? = null,
        zipCode: String? = null,
        country: String? = null,
    ) {
        propertyName?.let {
            addPropertyState = addPropertyState.copy(propertyName = it)
        }
        propertyType?.let {
            addPropertyState = addPropertyState.copy(propertyType = it)
        }
        street?.let {
            addPropertyState = addPropertyState.copy(street = it)
        }
        streetNumber?.let {
            addPropertyState = addPropertyState.copy(streetNumber = it)
        }
        city?.let {
            addPropertyState = addPropertyState.copy(city = it)
        }
        zipCode?.let {
            addPropertyState = addPropertyState.copy(zipCode = it)
        }
        country?.let {
            addPropertyState = addPropertyState.copy(country = it)
        }
        addPropertyState =
            addPropertyState.copy(FK_partnershipID = currentPartnership.partnershipID)
    }

    /**
     * Handles the addition of a property using [propertyService].
     */
    fun handlePropertyAdd() {
        viewModelScope.launch {
            try {
                propertyService.addProperty(
                    getToken(),
                    addPropertyState,
                )
                getData()
            } catch (e: Exception) {
                println("Error: ${e.message}")
            } finally {
                println("Finally")
            }
        }
    }
}
