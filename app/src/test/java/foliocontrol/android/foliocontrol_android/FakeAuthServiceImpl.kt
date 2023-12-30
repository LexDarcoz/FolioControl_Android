package foliocontrol.android.foliocontrol_android

import foliocontrol.android.foliocontrolandroid.data.repository.AuthService
import foliocontrol.android.foliocontrolandroid.domain.LoginCredentials
import foliocontrol.android.foliocontrolandroid.domain.Partnership
import foliocontrol.android.foliocontrolandroid.domain.User

class FakeAuthServiceImpl : AuthService {
    var activeUser: User? = null

    override suspend fun login(
        loginCredentials: LoginCredentials, updateTokenState: (String) -> Unit
    ): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun getPartnershipsForLoggedInUser(token: String): List<Partnership> {
        return listOf(
            Partnership(
                partnershipID = 1, name = "Test Partnership"
            ), Partnership(
                partnershipID = 2, name = "Test Partnership 2"
            )
        )
    }

    override suspend fun getUserWithToken(token: String): User {
        return activeUser ?: throw Exception("User not found")
    }

    override suspend fun saveUserByToken(token: String, user: User) {
        activeUser = User(userID = 1, name = "John Doe", email = "john@example.com")
    }
}