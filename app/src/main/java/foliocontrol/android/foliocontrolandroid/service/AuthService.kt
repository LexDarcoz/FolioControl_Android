package foliocontrol.android.foliocontrolandroid.service

import android.content.Context
import foliocontrol.android.foliocontrolandroid.data.LoginState
import foliocontrol.android.foliocontrolandroid.data.Partnership

interface AuthService {

    fun configureAuth(context: Context)

    suspend fun login(
        loginState: LoginState,
        updateTokenState: (String) -> Unit
    ): Boolean

    fun logOut(onComplete: () -> Unit)

    suspend fun getPartnershipsForLoggedInUser(token: String): List<Partnership>
}
