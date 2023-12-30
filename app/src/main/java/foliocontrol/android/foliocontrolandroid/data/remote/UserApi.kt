package foliocontrol.android.foliocontrolandroid.data.remote

import android.util.Log
import foliocontrol.android.foliocontrolandroid.data.remote.common.createRetrofit
import foliocontrol.android.foliocontrolandroid.domain.LoginCredentials
import foliocontrol.android.foliocontrolandroid.domain.Token
import foliocontrol.android.foliocontrolandroid.domain.User
import foliocontrol.android.foliocontrolandroid.domain.UserDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST

/**
 * Retrofit API interface for handling user-related requests.
 */
interface UserApi {
    @Headers("Accept: application/json")
    @POST("api/user/login")
    suspend fun login(
        @Body userState: LoginCredentials,
    ): Token

    @GET("api/user")
    suspend fun getUser(
        @Header("Authorization") token: String,
    ): User

    @Headers("Accept: application/json")
    @POST("api/user/update")
    suspend fun saveUser(
        @Header("Authorization") token: String,
        @Body userState: UserDto,
    )
}

private val userApi = createRetrofit(UserApi::class.java)

/**
 * Function to handle user login request.
 *
 * @param loginCredentials The [LoginCredentials] object.
 * @param updateTokenState Callback to update the token state.
 * @return `true` if the login is successful, `false` otherwise.
 */
suspend fun UserLoginRequest(
    loginCredentials: LoginCredentials,
    updateTokenState: (String) -> Unit,
): Boolean {
    var token: String? = null
    try {
        val call = userApi.login(loginCredentials)
        token = call.token
    } catch (e: Exception) {
        Log.e("TESTING", "UserLoginRequest: ", e)
    }
    return if (token != null) {
        updateTokenState(token)
        true
    } else {
        false
    }
}

/**
 * Function to get user information with the provided token.
 *
 * @param token The authorization token.
 * @return [User] object representing the user information.
 */
suspend fun getUser(token: String): User {
    return userApi.getUser(token = token)
}

/**
 * Function to save user information.
 *
 * @param token The authorization token.
 * @param user The [User] object representing the user information.
 */
suspend fun saveUser(
    token: String,
    user: User,
) {
    try {
        val userDto = UserDto(
            name = user.name,
            firstName = user.firstName,
            lastName = user.lastName,
            street = user.street,
            streetNumber = user.streetNumber,
            zipCode = user.zipCode,
            city = user.city,
            country = user.country,
            email = user.email,
        )

        val call = userApi.saveUser(token, userDto)
    } catch (e: Exception) {
        Log.e("TESTING", "UserLoginRequest: ", e)
    }
}
