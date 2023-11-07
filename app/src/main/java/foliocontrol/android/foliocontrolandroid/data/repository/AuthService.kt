package foliocontrol.android.foliocontrolandroid.data.repository

import android.content.Context
import foliocontrol.android.foliocontrolandroid.domain.dataModels.LoginState
import foliocontrol.android.foliocontrolandroid.domain.dataModels.Partnership

interface AuthService {

    fun configureAuth(context: Context)

    suspend fun login(
        loginState: LoginState,
        updateTokenState: (String) -> Unit
    ): Boolean

    fun logOut(onComplete: () -> Unit)

    suspend fun getPartnershipsForLoggedInUser(token: String): List<Partnership>
}
