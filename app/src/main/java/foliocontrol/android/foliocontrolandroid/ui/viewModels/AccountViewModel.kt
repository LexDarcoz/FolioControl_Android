package foliocontrol.android.foliocontrolandroid.ui.viewModels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import foliocontrol.android.foliocontrolandroid.data.local.getEncryptedPreference
import foliocontrol.android.foliocontrolandroid.data.repository.AuthServiceImpl
import foliocontrol.android.foliocontrolandroid.domain.User
import foliocontrol.android.foliocontrolandroid.ui.viewModels.common.UiState
import kotlinx.coroutines.launch
import java.io.IOException

class AccountViewModel : ViewModel() {

    private val authService = AuthServiceImpl()
    var user: User by mutableStateOf(User())
        private set
    var uiState: UiState by mutableStateOf(UiState.LoggedOut("You are not logged in"))
        private set


    fun getData() {
        viewModelScope.launch {
            uiState = UiState.Loading
            try {
                user = authService.getUserWithToken(getToken())
            } catch (e: IOException) {
                uiState = UiState.Error("Failed to connect to server: ${e.message}")
            } catch (e: Exception) {
                uiState = UiState.Error("Something went very wrong: ${e.message}")
            }
            uiState = UiState.Success("Loaded data succesfully")
        }

    }

    fun getToken(): String {
        return getEncryptedPreference("token")
    }


    fun handleUserEdit(
        name: String? = null,
        firstName: String? = null,
        lastName: String? = null,
        email: String? = null,
        street:String? = null,
        streetNumber: String? = null,
        city: String? = null,
        zipCode: String? = null,
        country: String? = null
    ) {
        name?.let {
            user = user.copy(name = it)
        }
        firstName?.let {
            user = user.copy(firstName = it)
        }
        lastName?.let {
            user = user.copy(lastName = it)
        }
        email?.let {
            user = user.copy(email = it)
        }
        street?.let {
            user = user.copy(street = it)
        }
        streetNumber?.let {
            user = user.copy(streetNumber = it)
        }
        city?.let {
            user = user.copy(city = it)
        }
        zipCode?.let {
            user = user.copy(zipCode = it)
        }
        country?.let {
            user = user.copy(country = it)
        }
    }
    fun handleUserSave() {
        viewModelScope.launch {
            try {
                authService.saveUserByToken(
                    getEncryptedPreference("token"),
                    user
                )
            } catch (e: Exception) {
                println("Error: ${e.message}")
            } finally {
                println("Finally")
            }
        }
    }

}