package foliocontrol.android.foliocontrolandroid.auth

import android.content.Context
import foliocontrol.android.foliocontrolandroid.api.UserLoginRequest
import foliocontrol.android.foliocontrolandroid.context.LoginState
import foliocontrol.android.foliocontrolandroid.context.SignUpState

class AuthServiceImpl : AuthService {
    override fun configureAuth(context: Context) {
        TODO("Not yet imple  mented")
    }

    override fun signUp(signUpState: SignUpState, onComplete: () -> Unit) {
    }

    override fun login(
        loginState: LoginState,
        onComplete: () -> Unit,
        updateUserState: (String) -> Unit
    ): Boolean {
        return if (UserLoginRequest(loginState, updateUserState)) {
            onComplete()
            println("User succesfully logged in")
            true
        } else {
            println("User failed to log in")
            false
        }
    }

    override fun logOut(onComplete: () -> Unit) {
        TODO("Not yet implemented")
    }
}
