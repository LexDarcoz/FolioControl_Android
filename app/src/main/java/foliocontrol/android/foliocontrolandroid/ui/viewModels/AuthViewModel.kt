package foliocontrol.android.foliocontrolandroid.ui.viewModels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import foliocontrol.android.foliocontrolandroid.data.local.auth.TokenRepo
import foliocontrol.android.foliocontrolandroid.data.local.database.AccountDatabase
import foliocontrol.android.foliocontrolandroid.data.local.database.PartnershipDatabase
import foliocontrol.android.foliocontrolandroid.data.local.database.PropertyDatabase
import foliocontrol.android.foliocontrolandroid.data.local.removeEncryptedPreference
import foliocontrol.android.foliocontrolandroid.data.local.saveEncryptedPreference
import foliocontrol.android.foliocontrolandroid.data.repository.AuthServiceImpl
import foliocontrol.android.foliocontrolandroid.domain.LoginCredentials
import foliocontrol.android.foliocontrolandroid.ui.viewModels.common.UiState
import kotlinx.coroutines.launch

/**
 * ViewModel responsible for managing authentication-related logic and user login state.
 *
 * @property propertyRepo The local database representing the property repository.
 * @property partnershipRepo The local database representing the partnership repository.
 * @property accountRepo The local database representing the account repository.
 */
class AuthViewModel(
    private val propertyRepo: PropertyDatabase,
    private val partnershipRepo: PartnershipDatabase,
    private val accountRepo: AccountDatabase,
    private val tokenRepo: TokenRepo,
) : ViewModel() {
    private val authService = AuthServiceImpl()

    var loginUiState: UiState by mutableStateOf(
        UiState.LoggedOut("You are not logged in"),
    )
        private set
    var loginCredentials by mutableStateOf(LoginCredentials())
        private set
    var userToken by mutableStateOf("")
        private set

    init {
        try {
            if (getToken().isNotEmpty()) {
                userToken = getToken()
                loginUiState = UiState.Success("You have logged in")
            }
        } catch (e: Exception) {
            println("User not valid token")
        }
    }

    fun updateTokenState(token: String? = null) {
        token?.let {
            userToken = it
        }
    }

    /**
     * Retrieves the saved user token from encrypted preferences.
     *
     * @return The user token.
     */
    fun getToken(): String {
        return tokenRepo.getToken()
    }

    /**
     * Resets the user token to an empty string, indicating a logged-out state.
     */
    fun resetToken() {
        userToken = ""
    }

    /**
     * Updates the login credentials with the provided [email] and [password].
     *
     * @param email The user's email.
     * @param password The user's password.
     */
    fun updateLoginState(
        email: String? = null,
        password: String? = null,
    ) {
        email?.let {
            loginCredentials = loginCredentials.copy(email = it)
        }
        password?.let {
            loginCredentials = loginCredentials.copy(password = it)
        }
    }

    /**
     * Initiates the login process, updating the [loginUiState] accordingly.
     */
    fun login() {
        viewModelScope.launch {
            loginUiState = UiState.Loading

            try {
                var auth = authService.login(loginCredentials, updateTokenState = { token ->
                    updateTokenState(token)
                })

                if (auth) {
                    saveEncryptedPreference("token", userToken)
                    loginUiState = UiState.Success("You have logged in")
                } else {
                    loginUiState = UiState.LoggedOut(
                        "Something went wrong logging in, check credentials",
                    )
                }
            } catch (e: Exception) {
                loginUiState = UiState.LoggedOut(e.localizedMessage ?: "You logged out")
            }
        }
    }

    /**
     * Logs the user out, clearing saved preferences and local databases.
     */
    fun logOut() {
        viewModelScope.launch {
            try {
                resetToken()
                removeEncryptedPreference("token")
                partnershipRepo.clearAllTables()
                propertyRepo.clearAllTables()
                accountRepo.clearAllTables()
            } catch (e: Exception) {
                println("Error logging out")
            }
        }

        loginUiState = UiState.LoggedOut("You logged out")
    }
}
