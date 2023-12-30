package foliocontrol.android.foliocontrolandroid.data.repository

import foliocontrol.android.foliocontrolandroid.domain.LoginCredentials
import foliocontrol.android.foliocontrolandroid.domain.Partnership
import foliocontrol.android.foliocontrolandroid.domain.User

/**
 * Interface defining authentication-related operations.
 */
interface AuthService {
    /**
     * Suspended function to perform user login.
     *
     * @param loginCredentials The login credentials including email and password.
     * @param updateTokenState Callback to update the token state upon successful login.
     * @return `true` if the login is successful, `false` otherwise.
     */
    suspend fun login(
        loginCredentials: LoginCredentials,
        updateTokenState: (String) -> Unit,
    ): Boolean

    /**
     * Suspended function to retrieve partnerships for the logged-in user.
     *
     * @param token The authentication token.
     * @return List of [Partnership] objects associated with the logged-in user.
     */
    suspend fun getPartnershipsForLoggedInUser(token: String): List<Partnership>

    /**
     * Suspended function to retrieve user information using the provided token.
     *
     * @param token The authentication token.
     * @return [User] object representing the logged-in user.
     */
    suspend fun getUserWithToken(token: String): User

    /**
     * Suspended function to save user information using the provided token.
     *
     * @param token The authentication token.
     * @param user The [User] object to be saved.
     */
    suspend fun saveUserByToken(
        token: String,
        user: User,
    )
}
