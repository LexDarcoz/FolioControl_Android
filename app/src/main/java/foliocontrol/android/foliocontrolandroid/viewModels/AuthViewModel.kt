package foliocontrol.android.foliocontrolandroid.viewModels

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import foliocontrol.android.foliocontrolandroid.auth.AuthServiceImpl
import foliocontrol.android.foliocontrolandroid.data.LoginState
import foliocontrol.android.foliocontrolandroid.data.Token
import foliocontrol.android.foliocontrolandroid.data.User
import kotlinx.coroutines.launch

sealed interface LoginUiState {

    data class Success(val message: Boolean) : LoginUiState
    data class LoggedOut(val message: String) : LoginUiState
    data class Error(val message: String) : LoginUiState
    data object Loading : LoginUiState
}

class AuthViewModel : ViewModel() {
    lateinit var navigateTo: (String) -> Unit
    private val authService = AuthServiceImpl()
    var loginUiState: LoginUiState by mutableStateOf(
        LoginUiState.LoggedOut("You are not logged in")
    )
        private set


    var loginState = mutableStateOf(LoginState())
        private set

    var userToken = mutableStateOf(Token())
        private set


    fun updateTokenState(
        token: String? = null,
    ) {
        Log.d("Token", "tokenLogin: $token")
        token?.let {
            userToken.value = userToken.value.copy(token = it)
        }
    }

    fun getToken(): Token {
        return this.userToken.value
    }

    fun resetToken() {
        userToken.value = userToken.value.copy(token = "")
    }

    fun verify(Token: Token) {


    }

    fun updateLoginState(
        email: String? = null, password: String? = null
    ) {
        email?.let {
            loginState.value = loginState.value.copy(email = it)
        }
        password?.let {
            loginState.value = loginState.value.copy(password = it)
        }
    }

    fun login() {

    viewModelScope.launch {
        loginUiState = LoginUiState.Loading

        try {
            var auth = authService.login(loginState.value, updateTokenState = { token ->
                updateTokenState(token)

            })
            loginUiState = LoginUiState.Success(true)


        } catch (e: Exception) {
            loginUiState = LoginUiState.LoggedOut(e.localizedMessage ?: "You logged out")
        }
    }

    }

    fun logOut() {
        resetToken()
        loginUiState = LoginUiState.LoggedOut("You logged out")

    }
}
