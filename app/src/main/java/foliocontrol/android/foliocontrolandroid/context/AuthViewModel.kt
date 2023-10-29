package foliocontrol.android.foliocontrolandroid.context

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import foliocontrol.android.foliocontrolandroid.auth.AuthServiceImpl
import kotlinx.coroutines.launch

sealed interface LoginUiState {

    data class Success(val message: Boolean) : LoginUiState
    data class LoggedOut(val message: String) : LoginUiState
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

    var userState = mutableStateOf(User())
        private set
    var userToken = mutableStateOf(Token())
        private set

    fun updateUserState(
        email: String? = null,
        name: String? = null,
        lastName: String? = null,
        firstName: String? = null,
    ) {
        email?.let {
            userState.value = userState.value.copy(email = it)
        }
        name?.let {
            userState.value = userState.value.copy(name = it)
        }
        firstName?.let {
            userState.value = userState.value.copy(firstName = it)
        }
        lastName?.let {
            userState.value = userState.value.copy(lastName = it)
        }
    }
    fun updateTokenState(
        token: String? = null,
    ) {
        Log.d("Token", "tokenLogin: $token")
        token?.let {
            userToken.value = userToken.value.copy(token = it)
        }
    }

    fun getToken() : Token{
        return this.userToken.value
    }

    fun verify(Token: Token){


    }

    fun updateLoginState(
        email: String? = null,
        password: String? = null
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
                var auth = authService.login(
                    loginState.value,
                    updateTokenState = { token ->
                        updateTokenState(token)
                    }
                )
                loginUiState = LoginUiState.Success(true)

            } catch (e: Exception) {
                loginUiState = LoginUiState.LoggedOut(e.localizedMessage ?: "You logged out")
            }
        }
    }

    fun logOut() {
        loginUiState = LoginUiState.LoggedOut("You logged out")
    }
}
