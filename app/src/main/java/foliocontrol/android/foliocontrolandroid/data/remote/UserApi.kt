package foliocontrol.android.foliocontrolandroid.data.remote

import android.util.Log
import foliocontrol.android.foliocontrolandroid.data.remote.common.createRetrofit
import foliocontrol.android.foliocontrolandroid.domain.LoginCredentials
import foliocontrol.android.foliocontrolandroid.domain.Token
import foliocontrol.android.foliocontrolandroid.domain.User
import foliocontrol.android.foliocontrolandroid.domain.UserDto
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST

interface UserApi {

    @Headers("Accept: application/json")
    @POST("api/user/login")
    suspend fun login(@Body userState: LoginCredentials): Token

    @GET("api/user")
    suspend fun getUser(@Header("Authorization") token: String): User

    @Headers("Accept: application/json")
    @POST("api/user/update")
    suspend fun saveUser(
        @Header("Authorization") token: String, @Body userState: UserDto
    )
}

private val userApi = createRetrofit(UserApi::class.java)
suspend fun UserLoginRequest(
    loginCredentials: LoginCredentials, updateTokenState: (String) -> Unit
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

suspend fun getUser(token: String): User {


    return userApi.getUser(token = token)
}

suspend fun saveUser(token: String, user: User) {
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
            email = user.email
        )

        val call = userApi.saveUser(token, userDto)
    } catch (e: Exception) {
        Log.e("TESTING", "UserLoginRequest: ", e)
    }
}
