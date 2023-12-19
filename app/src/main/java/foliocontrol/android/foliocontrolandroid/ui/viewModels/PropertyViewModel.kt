package foliocontrol.android.foliocontrolandroid.ui.viewModels

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import foliocontrol.android.foliocontrolandroid.data.local.database.PartnershipDatabase
import foliocontrol.android.foliocontrolandroid.data.local.database.PropertyDatabase
import foliocontrol.android.foliocontrolandroid.data.local.getEncryptedPreference
import foliocontrol.android.foliocontrolandroid.data.local.schema.asDomainModel
import foliocontrol.android.foliocontrolandroid.data.repository.AuthServiceImpl
import foliocontrol.android.foliocontrolandroid.data.repository.PropertyServiceImpl
import foliocontrol.android.foliocontrolandroid.domain.Partnership
import foliocontrol.android.foliocontrolandroid.domain.Premise
import foliocontrol.android.foliocontrolandroid.domain.Property
import foliocontrol.android.foliocontrolandroid.domain.PropertyDocument
import foliocontrol.android.foliocontrolandroid.domain.asPartnershipRoomEntity
import foliocontrol.android.foliocontrolandroid.domain.asPropertyRoomEntity
import foliocontrol.android.foliocontrolandroid.ui.viewModels.common.UiState
import java.io.IOException
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class PropertyViewModel(
    private val propertyRepo: PropertyDatabase,
    private val partnershipRepo: PartnershipDatabase
) : ViewModel() {

    var uiState: UiState by mutableStateOf(
        UiState.LoggedOut("You are not logged in")
    )
        private set

    private val authService = AuthServiceImpl()
    private val propertyService = PropertyServiceImpl()
    var currentPartnership by mutableStateOf(Partnership())
        private set

    var propertyDocuments by mutableStateOf(emptyList<PropertyDocument>())
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
    var isAddPropertyDialogOpen by mutableStateOf(false)
    var isSearchBarEnabled by mutableStateOf(false)
    var addPropertyState by mutableStateOf(Property())
        private set

    fun getData() {
        viewModelScope.launch {
            uiState = UiState.Loading
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

    fun filterProperties(query: String) {
        filteredList = propertyListState.filter {
            it.propertyName.contains(query, ignoreCase = true) ||
                it.propertyType.contains(query, ignoreCase = true)
        }
    }

    fun selectProperty(property: Property) {
        propertyState = property
        Log.i("TEST", "selectProperty: $property")
        propertyDocuments = emptyList()
        propertyPremises = emptyList()
    }

    suspend fun getPropertyListForPartnership() {
        try {
            propertyListState = propertyService.getProperties(
                getEncryptedPreference("token"),
                currentPartnership
            )
            if (propertyListState.isNotEmpty()) {
                propertyRepo.dropTable(currentPartnership.partnershipID)
            }
            propertyRepo.insertAll(propertyListState.map { it.asPropertyRoomEntity() })
        } catch (e: Exception) {
            println("Error: ${e.message}")
        } finally {
            println("Finally")
        }
    }

    suspend fun getPartnershipListForLoggedInUser() {
        partnershipListState =
            authService.getPartnershipsForLoggedInUser(getEncryptedPreference("token"))
        partnershipRepo.insertAllPartnerships(
            partnershipListState.map {
                it.asPartnershipRoomEntity()
            }
        )
    }

    fun switchPartnership(partnership: Partnership) {
        propertyListState = emptyList()
        currentPartnership = partnership
    }

    fun defaultPartnership() {
        currentPartnership = partnershipListState[0]
    }

    fun getDataForActiveProperty() {
        viewModelScope.launch {
            uiState = UiState.Loading
            try {
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

    suspend fun getPremisesForProperty(property: Property) {
        propertyPremises = propertyService.getPremisesForProperty(
            getEncryptedPreference("token"),
            property
        )
    }

    suspend fun getDocumentsForProperty(property: Property) {
        propertyDocuments = propertyService.getDocumentsForProperty(
            getEncryptedPreference("token"),
            property
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
        country: String? = null
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

    fun handlePropertySave() {
        viewModelScope.launch {
            Log.i("TEST", "Update: $propertyState")
            try {
                propertyService.savePropertyByPropertyID(
                    getEncryptedPreference("token"),
                    propertyState
                )
            } catch (e: Exception) {
                println("Error: ${e.message}")
            } finally {
                println("Finally")
            }
        }
    }

    fun handlePropertyDelete(propertyId: Int) {
        viewModelScope.launch {
            try {
                propertyService.deletePropertyByPropertyID(
                    getEncryptedPreference("token"),
                    propertyId

                )
                getData()
            } catch (e: Exception) {
                println("Error: ${e.message}")
            } finally {
                println("Finally")
            }
        }
    }

    fun togglePropertyAddDialog() {
        isAddPropertyDialogOpen = !isAddPropertyDialogOpen
    }

    fun toggleSearchBar() {
        isSearchBarEnabled = !isSearchBarEnabled
        filteredList = propertyListState
    }

    fun handlePropertyAddEdit(
        propertyName: String? = null,
        propertyType: String? = null,
        street: String? = null,
        streetNumber: String? = null,
        city: String? = null,
        zipCode: String? = null,
        country: String? = null
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

    fun handlePropertyAdd() {
        viewModelScope.launch {
            try {
                propertyService.addProperty(
                    getEncryptedPreference("token"),
                    addPropertyState
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
