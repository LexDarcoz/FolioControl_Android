package foliocontrol.android.foliocontrolandroid.viewModels

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import foliocontrol.android.foliocontrolandroid.data.LoginState
import foliocontrol.android.foliocontrolandroid.data.Partnership
import foliocontrol.android.foliocontrolandroid.data.Token
import foliocontrol.android.foliocontrolandroid.data.repository.AuthServiceImpl
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
    var partnershipList by mutableStateOf(listOf<Partnership>())
        private set
    var currentPartnership by mutableStateOf(Partnership())
        private set
    var loginCredentials = mutableStateOf(LoginState())
        private set
    var userToken by mutableStateOf("")
        private set

    fun getPartnershipListForLoggedInUser(token: String) {
        viewModelScope.launch {
            try {
                partnershipList = authService.getPartnershipsForLoggedInUser(token)
                switchPartnership(partnershipList[0])
            } catch (e: Exception) {
                println("Error: ${e.message}")
            } finally {
                println("Finally")
            }
        }
    }

    var loginUiState: LoginUiState by mutableStateOf(
        LoginUiState.LoggedOut("You are not logged in")
    )
        private set

    fun updateTokenState(
        token: String? = null
    ) {
        Log.d("Token", "tokenLogin: $token")
        token?.let {
            userToken = it
        }
    }

    fun switchPartnership(partnership: Partnership) {
        Log.i("TESTING", "switchPartnership:$partnership ")
        currentPartnership = partnership
    }

    fun getToken(): String {
        return this.userToken
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
            loginCredentials.value = loginCredentials.value.copy(email = it)
        }
        password?.let {
            loginCredentials.value = loginCredentials.value.copy(password = it)
        }
    }

    fun login() {
        viewModelScope.launch {
            loginUiState = LoginUiState.Loading

            try {
                var auth = authService.login(loginCredentials.value, updateTokenState = { token ->
                    updateTokenState(token)
                })
                getPartnershipListForLoggedInUser(userToken)

                loginUiState = LoginUiState.Success("You have logged in")
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
