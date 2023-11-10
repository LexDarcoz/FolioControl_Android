package foliocontrol.android.foliocontrolandroid.data.repository

import android.content.Context
import foliocontrol.android.foliocontrolandroid.domain.LoginCredentials
import foliocontrol.android.foliocontrolandroid.domain.Partnership

interface AuthService {

    fun configureAuth(context: Context)

    suspend fun login(
        loginCredentials: LoginCredentials,
        updateTokenState: (String) -> Unit
    ): Boolean

    fun logOut(onComplete: () -> Unit)

    suspend fun getPartnershipsForLoggedInUser(token: String): List<Partnership>
}
