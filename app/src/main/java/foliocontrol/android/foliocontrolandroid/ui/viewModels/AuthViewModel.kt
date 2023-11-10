package foliocontrol.android.foliocontrolandroid.ui.viewModels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import foliocontrol.android.foliocontrolandroid.data.local.getEncryptedPreference
import foliocontrol.android.foliocontrolandroid.data.local.removeEncryptedPreference
import foliocontrol.android.foliocontrolandroid.data.local.saveEncryptedPreference
import foliocontrol.android.foliocontrolandroid.data.repository.AuthServiceImpl
import foliocontrol.android.foliocontrolandroid.domain.LoginCredentials
import foliocontrol.android.foliocontrolandroid.domain.Token
import foliocontrol.android.foliocontrolandroid.ui.viewModels.common.UiState
import kotlinx.coroutines.launch

class AuthViewModel : ViewModel() {
    lateinit var navigateTo: (String) -> Unit
    private val authService = AuthServiceImpl()


    var loginUiState: UiState by mutableStateOf(
        UiState.LoggedOut("You are not logged in")
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
                        "Something went wrong logging in, check credentials"
                    )
                }
            } catch (e: Exception) {
                loginUiState = UiState.LoggedOut(e.localizedMessage ?: "You logged out")
            }
        }
    }

    fun logOut() {
        resetToken()
        removeEncryptedPreference("token")
        loginUiState = UiState.LoggedOut("You logged out")
    }

    fun setLoading(boolean: Boolean) {
        if (boolean) {
            loginUiState = UiState.Loading
        } else {
            loginUiState = UiState.Success("Succesfully got data")
        }
    }
}
