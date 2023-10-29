package foliocontrol.android.foliocontrolandroid.auth

import android.content.Context
import foliocontrol.android.foliocontrolandroid.api.UserLoginRequest
import foliocontrol.android.foliocontrolandroid.context.LoginState


class AuthServiceImpl : AuthService {
    override fun configureAuth(context: Context) {
        TODO("Not yet imple  mented")
    }

    override suspend fun login(
        loginState: LoginState,
        updateTokenState: (String) -> Unit,
    ): Boolean {
        return if (UserLoginRequest(loginState, updateTokenState)) {
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
