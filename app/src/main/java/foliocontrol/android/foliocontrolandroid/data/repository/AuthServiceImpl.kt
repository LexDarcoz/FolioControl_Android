package foliocontrol.android.foliocontrolandroid.data.repository

import foliocontrol.android.foliocontrolandroid.data.remote.UserLoginRequest
import foliocontrol.android.foliocontrolandroid.data.remote.getUser
import foliocontrol.android.foliocontrolandroid.data.remote.getUserPartnerships
import foliocontrol.android.foliocontrolandroid.data.remote.saveUser
import foliocontrol.android.foliocontrolandroid.domain.LoginCredentials
import foliocontrol.android.foliocontrolandroid.domain.Partnership
import foliocontrol.android.foliocontrolandroid.domain.User

class AuthServiceImpl : AuthService {
    override suspend fun login(
        loginCredentials: LoginCredentials,
        updateTokenState: (String) -> Unit
    ): Boolean {
        return if (UserLoginRequest(loginCredentials, updateTokenState)) {
            println("User succesfully logged in")
            true
        } else {
            println("User failed to log in")
            false
        }
    }

    override suspend fun getPartnershipsForLoggedInUser(token: String): List<Partnership> {
        return getUserPartnerships(token)
    }

    override suspend fun getUserWithToken(token: String): User {
        return getUser(token)
    }

    override suspend fun saveUserByToken(token: String, user: User) {
        return saveUser(token, user)
    }
}
