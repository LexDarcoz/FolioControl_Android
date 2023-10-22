package foliocontrol.android.foliocontrolandroid.context

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import foliocontrol.android.foliocontrolandroid.auth.AuthServiceImpl

class AuthViewModel : ViewModel() {
    lateinit var navigateTo: (String) -> Unit
    private val authService = AuthServiceImpl()
    var loginState = mutableStateOf(LoginState())
        private set

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

    var signUpState = mutableStateOf(SignUpState())
        private set

    fun updateSignUpState(
        username: String? = null,
        email: String? = null,
        password: String? = null
    ) {
        username?.let {
            signUpState.value = signUpState.value.copy(username = it)
        }
        email?.let {
            signUpState.value = signUpState.value.copy(email = it)
        }
        password?.let {
            signUpState.value = signUpState.value.copy(password = it)
        }
    }

    // Navigation
    fun showSignUp() {
        authService.signUp(SignUpState(), onComplete = { navigateTo("SignUp") })
    }

    fun showLogin() {
        navigateTo("Login")
    }

    fun login() {
        navigateTo("Home")
    }

    fun logOut() {
        navigateTo("Login")
    }
}
