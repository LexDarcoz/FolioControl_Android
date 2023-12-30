package foliocontrol.android.foliocontrolandroid.data.repository

import foliocontrol.android.foliocontrolandroid.data.remote.UserLoginRequest
import foliocontrol.android.foliocontrolandroid.data.remote.getUser
import foliocontrol.android.foliocontrolandroid.data.remote.getUserPartnerships
import foliocontrol.android.foliocontrolandroid.data.remote.saveUser
import foliocontrol.android.foliocontrolandroid.domain.LoginCredentials
import foliocontrol.android.foliocontrolandroid.domain.Partnership
import foliocontrol.android.foliocontrolandroid.domain.User

/**
 * Implementation of [AuthService] providing authentication-related operations.
 */
class AuthServiceImpl : AuthService {
    /**
     * Suspended function to perform user login.
     *
     * @param loginCredentials The login credentials including email and password.
     * @param updateTokenState Callback to update the token state upon successful login.
     * @return `true` if the login is successful, `false` otherwise.
     */
    override suspend fun login(
        loginCredentials: LoginCredentials,
        updateTokenState: (String) -> Unit,
    ): Boolean {
        return if (UserLoginRequest(loginCredentials, updateTokenState)) {
            println("User succesfully logged in")
            true
        } else {
            println("User failed to log in")
            false
        }
    }

    /**
     * Suspended function to retrieve partnerships for the logged-in user.
     *
     * @param token The authentication token.
     * @return List of [Partnership] objects associated with the logged-in user.
     */
    override suspend fun getPartnershipsForLoggedInUser(token: String): List<Partnership> {
        return getUserPartnerships(token)
    }

    /**
     * Suspended function to retrieve user information using the provided token.
     *
     * @param token The authentication token.
     * @return [User] object representing the logged-in user.
     */
    override suspend fun getUserWithToken(token: String): User {
        return getUser(token)
    }

    /**
     * Suspended function to save user information using the provided token.
     *
     * @param token The authentication token.
     * @param user The [User] object to be saved.
     */
    override suspend fun saveUserByToken(
        token: String,
        user: User,
    ) {
        return saveUser(token, user)
    }
}
