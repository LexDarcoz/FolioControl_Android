package foliocontrol.android.foliocontrolandroid.data.repository

import foliocontrol.android.foliocontrolandroid.domain.LoginCredentials
import foliocontrol.android.foliocontrolandroid.domain.Partnership
import foliocontrol.android.foliocontrolandroid.domain.User

interface AuthService {
    suspend fun login(
        loginCredentials: LoginCredentials,
        updateTokenState: (String) -> Unit,
    ): Boolean

    suspend fun getPartnershipsForLoggedInUser(token: String): List<Partnership>

    suspend fun getUserWithToken(token: String): User

    suspend fun saveUserByToken(
        token: String,
        user: User,
    )
}
