package foliocontrol.android.foliocontrolandroid.ui.viewModels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import foliocontrol.android.foliocontrolandroid.data.local.database.AccountDatabase
import foliocontrol.android.foliocontrolandroid.data.local.getEncryptedPreference
import foliocontrol.android.foliocontrolandroid.data.local.schema.asDomainModel
import foliocontrol.android.foliocontrolandroid.data.repository.AuthServiceImpl
import foliocontrol.android.foliocontrolandroid.domain.User
import foliocontrol.android.foliocontrolandroid.domain.asUserRoomEntity
import foliocontrol.android.foliocontrolandroid.ui.viewModels.common.UiState
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import java.io.IOException

/**
 * ViewModel responsible for managing user account-related logic and data.
 *
 * @property accountRepo The local database representing the account repository.
 */
class AccountViewModel(
    private val accountRepo: AccountDatabase,
) : ViewModel() {
    private val authService = AuthServiceImpl()
    var user: User by mutableStateOf(User())
        private set

    var uiState: UiState by mutableStateOf(UiState.LoggedOut("You are not logged in"))
        private set

    /**
     * Initiates the process of fetching user data. Updates [uiState] based on the success or failure of the operation.
     */
    fun getData() {
        viewModelScope.launch {
            uiState = UiState.Loading
            try {
                getUserData()
            } catch (e: IOException) {
                uiState = UiState.Offline("Failed to connect to server: ${e.message}")
            } catch (e: Exception) {
                uiState = UiState.Error("Something went very wrong: ${e.message}")
            }
        }
    }

    /**
     * Retrieves the user token from encrypted preferences.
     *
     * @return The user token.
     */
    fun getToken(): String {
        return getEncryptedPreference("token")
    }

    suspend fun getUserData() {
        try {
            var data = authService.getUserWithToken(getToken())
            user = data
            accountRepo.insertUser(user.asUserRoomEntity())
            uiState = UiState.Success("Loaded data successfully")
        } catch (e: IOException) {
            user = accountRepo.getUser().first().asDomainModel()
            uiState = UiState.Offline("Failed to connect to server: ${e.message}")
        }
    }

    /**
     * Handles user data editing, updating the relevant properties of the [user].
     *
     * @param name The user's name.
     * @param firstName The user's first name.
     * @param lastName The user's last name.
     * @param email The user's email.
     * @param street The user's street.
     * @param streetNumber The user's street number.
     * @param city The user's city.
     * @param zipCode The user's zip code.
     * @param country The user's country.
     */
    fun handleUserEdit(
        name: String? = null,
        firstName: String? = null,
        lastName: String? = null,
        email: String? = null,
        street: String? = null,
        streetNumber: String? = null,
        city: String? = null,
        zipCode: String? = null,
        country: String? = null,
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

    /**
     * Initiates the process of saving user data to the server.
     */
    fun handleUserSave() {
        viewModelScope.launch {
            try {
                authService.saveUserByToken(
                    getEncryptedPreference("token"),
                    user,
                )
            } catch (e: Exception) {
                println("Error: ${e.message}")
            } finally {
                println("Finally")
            }
        }
    }
}
