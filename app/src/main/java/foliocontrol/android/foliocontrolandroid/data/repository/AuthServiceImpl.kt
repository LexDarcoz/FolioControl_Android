package foliocontrol.android.foliocontrolandroid.data.repository

import android.content.Context
import foliocontrol.android.foliocontrolandroid.data.remote.UserLoginRequest
import foliocontrol.android.foliocontrolandroid.data.remote.getUserPartnerships
import foliocontrol.android.foliocontrolandroid.domain.dataModels.LoginState
import foliocontrol.android.foliocontrolandroid.domain.dataModels.Partnership

class AuthServiceImpl : AuthService {
    override fun configureAuth(context: Context) {
        TODO("Not yet imple  mented")
    }

    override suspend fun login(
        loginState: LoginState,
        updateTokenState: (String) -> Unit
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

    override suspend fun getPartnershipsForLoggedInUser(token: String): List<Partnership> {
        // TODO
        return getUserPartnerships(token)
    }
}
