package foliocontrol.android.foliocontrolandroid.domain.viewModels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import foliocontrol.android.foliocontrolandroid.data.local.getEncryptedPreference
import foliocontrol.android.foliocontrolandroid.data.local.removeEncryptedPreference
import foliocontrol.android.foliocontrolandroid.data.local.saveEncryptedPreference
import foliocontrol.android.foliocontrolandroid.data.repository.AuthServiceImpl
import foliocontrol.android.foliocontrolandroid.domain.dataModels.LoginState
import foliocontrol.android.foliocontrolandroid.domain.dataModels.Token
import kotlinx.coroutines.launch

sealed interface LoginUiState {

    data class Success(val message: String) : LoginUiState
    data class LoggedOut(val message: String) : LoginUiState
    data class Error(val message: String) : LoginUiState
    data object Loading : LoginUiState
}

class AuthViewModel : ViewModel() {
    lateinit var navigateTo: (String) -> Unit
    private val authService = AuthServiceImpl()

    var loginCredentials by mutableStateOf(LoginState())
        private set
    var userToken by mutableStateOf("")
        private set
    var loginUiState: LoginUiState by mutableStateOf(
        LoginUiState.LoggedOut("You are not logged in")
    )
        private set

    init {
        try {
            if (getToken().isNotEmpty()) {
                userToken = getToken()
                loginUiState = LoginUiState.Success("You have logged in")
            }
        } catch (e: Exception) {
            println("User not valid token")
        }
    }

    fun updateTokenState(
        token: String? = null
    ) {
        token?.let {
            userToken = it
        }
    }

    fun getToken(): String {
        return getEncryptedPreference("token")
    }

    fun resetToken() {
        userToken = ""
    }

    fun verify(Token: Token) {
        // TODO
    }

    fun updateLoginState(
        email: String? = null,
        password: String? = null
    ) {
        email?.let {
            loginCredentials = loginCredentials.copy(email = it)
        }
        password?.let {
            loginCredentials = loginCredentials.copy(password = it)
        }
    }

    fun login() {
        viewModelScope.launch {
            loginUiState = LoginUiState.Loading

            try {
                var auth = authService.login(loginCredentials, updateTokenState = { token ->
                    updateTokenState(token)
                })

                if (auth) {
                    saveEncryptedPreference("token", userToken)
                    loginUiState = LoginUiState.Success("You have logged in")
                } else {
                    loginUiState = LoginUiState.LoggedOut(
                        "Something went wrong logging in, check credentials"
                    )
                }
            } catch (e: Exception) {
                loginUiState = LoginUiState.LoggedOut(e.localizedMessage ?: "You logged out")
            }
        }
    }

    fun logOut() {
        resetToken()
        removeEncryptedPreference("token")
        loginUiState = LoginUiState.LoggedOut("You logged out")
    }
}
