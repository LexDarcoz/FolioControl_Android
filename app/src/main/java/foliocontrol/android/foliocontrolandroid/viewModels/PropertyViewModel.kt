package foliocontrol.android.foliocontrolandroid.viewModels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import foliocontrol.android.foliocontrolandroid.data.Property
import foliocontrol.android.foliocontrolandroid.data.repository.AuthServiceImpl

class PropertyViewModel : ViewModel() {
    private val authService = AuthServiceImpl()
    var propertyListState = mutableStateOf(listOf<Property>())
        private set

    var propertyState = mutableStateOf(Property())
        private set

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
            propertyState.value = propertyState.value.copy(propertyName = it)
        }
        propertyType?.let {
            propertyState.value = propertyState.value.copy(propertyType = it)
        }
        street?.let {
            propertyState.value = propertyState.value.copy(street = it)
        }
        streetNumber?.let {
            propertyState.value = propertyState.value.copy(streetNumber = it)
        }
        city?.let {
            propertyState.value = propertyState.value.copy(city = it)
        }
        zipCode?.let {
            propertyState.value = propertyState.value.copy(zipCode = it)
        }
        country?.let {
            propertyState.value = propertyState.value.copy(country = it)
        }
    }

    fun handlePropertySave() {
    }

    fun handlePropertyDelete() {
    }

    fun handlePropertyAdd() {
    }
}
