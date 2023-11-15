package foliocontrol.android.foliocontrolandroid.ui.viewModels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import foliocontrol.android.foliocontrolandroid.data.local.getEncryptedPreference
import foliocontrol.android.foliocontrolandroid.data.repository.AuthServiceImpl
import foliocontrol.android.foliocontrolandroid.data.repository.PropertyServiceImpl
import foliocontrol.android.foliocontrolandroid.domain.Partnership
import foliocontrol.android.foliocontrolandroid.domain.Property
import foliocontrol.android.foliocontrolandroid.ui.viewModels.common.UiState
import java.io.IOException
import kotlinx.coroutines.launch

class PropertyViewModel : ViewModel() {

    var uiState: UiState by mutableStateOf(
        UiState.LoggedOut("You are not logged in")
    )
        private set

    private val authService = AuthServiceImpl()
    private val propertyService = PropertyServiceImpl()

    var partnershipList by mutableStateOf(emptyList<Partnership>())
        private set
    var currentPartnership by mutableStateOf(Partnership())
        private set
    var propertyListState by mutableStateOf(emptyList<Property>())
        private set

    var propertyState by mutableStateOf(Property())
        private set

    fun getData() {
        viewModelScope.launch {
            uiState = UiState.Loading
            try {
                getPartnershipListForLoggedInUser()
                if (partnershipList.isNotEmpty() && currentPartnership.partnershipID == 0) {
                    defaultPartnership()
                }
                getPropertyListForPartnership()
                if (propertyListState.isNotEmpty()) {
                    uiState = UiState.Success("Succesfully retrieved data")
                } else {
                    uiState = UiState.Error("No data found")
                }
            } catch (e: IOException) {
                uiState = UiState.Error("Failed to connect to server: ${e.message}")
            } catch (e: Exception) {
                uiState = UiState.Error("Something went very wrong: ${e.message}")
            }
        }
    }

    fun selectProperty(property: Property) {
        propertyState = property
    }

    fun retryGetData() {
        getData()
    }

    suspend fun getPropertyListForPartnership() {
        try {
            propertyListState = propertyService.getProperties(
                getEncryptedPreference("token"),
                currentPartnership
            )
        } catch (e: Exception) {
            println("Error: ${e.message}")
        } finally {
            println("Finally")
        }
    }

    suspend fun getPartnershipListForLoggedInUser() {
        partnershipList =
            authService.getPartnershipsForLoggedInUser(getEncryptedPreference("token"))
    }

    fun switchPartnership(partnership: Partnership) {
        propertyListState = emptyList()
        currentPartnership = partnership
    }

    fun defaultPartnership() {
        currentPartnership = partnershipList[0]
    }

    fun handlePropertyEdit(
        propertyName: String? = null,
        propertyType: String? = null,
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

    fun handlePropertyAdd() {
    }
}
