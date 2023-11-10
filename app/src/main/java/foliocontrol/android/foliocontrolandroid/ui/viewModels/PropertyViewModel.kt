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

    var partnershipList by mutableStateOf(listOf<Partnership>())
        private set
    var currentPartnership by mutableStateOf(Partnership())
        private set
    var propertyListState by mutableStateOf(listOf<Property>())
        private set

    var propertyState by mutableStateOf(Property())
        private set

    fun getData() {
        uiState = UiState.Loading
        try {
            getPartnershipListForLoggedInUser()
            if (partnershipList.isNotEmpty() && currentPartnership.partnershipID == 0) {
                defaultPartnership()
            }
            getPropertyListForPartnership()
        } catch (e: IOException) {
            println("Error: ${e.message}")
            uiState = e.message?.let { UiState.Error(it) }!!

        } finally {
            println("Finally")
        }
        if (partnershipList.isNotEmpty()) {
            uiState = UiState.Success("Succesfully retrieved data")
        } else {
            uiState = UiState.Error("No data was found")
        }

    }

    fun selectProperty(property: Property) {
        propertyState = property
    }

    fun getPropertyListForPartnership() {
        viewModelScope.launch {
            try {
                propertyListState = propertyService.getProperties(
                    getEncryptedPreference("token"), currentPartnership
                )
            } catch (e: Exception) {
                println("Error: ${e.message}")
            } finally {
                println("Finally")
            }
        }
    }

    fun getPartnershipListForLoggedInUser() {
        viewModelScope.launch {
            try {
                partnershipList =
                    authService.getPartnershipsForLoggedInUser(getEncryptedPreference("token"))
            } catch (e: Exception) {
                println("Error: ${e.message}")
            } finally {
                println("Finally")
            }
        }
    }

    fun switchPartnership(partnership: Partnership) {
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
                    getEncryptedPreference("token"), propertyState
                )
            } catch (e: Exception) {
                println("Error: ${e.message}")
            } finally {
                println("Finally")
            }
        }
    }

    fun handlePropertyDelete() {
    }

    fun handlePropertyAdd() {
    }
}
