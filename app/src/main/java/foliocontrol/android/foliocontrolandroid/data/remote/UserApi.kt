package foliocontrol.android.foliocontrolandroid.data.remote

import android.util.Log
import foliocontrol.android.foliocontrolandroid.data.remote.common.createRetrofit
import foliocontrol.android.foliocontrolandroid.domain.LoginCredentials
import foliocontrol.android.foliocontrolandroid.domain.User
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
    suspend fun login(@Body userState: JsonObject): JsonObject

    @GET("api/user")
    suspend fun getUser(@Header("Authorization") token: String): JsonObject

    @POST("api/user/update")
    suspend fun saveUser(
        @Header("Authorization") token: String,
        @Body userState: JsonObject
    ): JsonObject
}

private val userApi = createRetrofit(UserApi::class.java)
suspend fun UserLoginRequest(
    loginCredentials: LoginCredentials,
    updateTokenState: (String) -> Unit
): Boolean {
    var body = buildJsonObject {
        put("email", JsonPrimitive(loginCredentials.email))
        put("password", JsonPrimitive(loginCredentials.password))
    }
    var token: String? = null
    try {
        val call: JsonObject = userApi.login(body)
        token = call.jsonObject["token"]?.jsonPrimitive?.content
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
    var user: User? = null
    var result: JsonObject? = null
    val call: JsonObject = userApi.getUser(token = token)
    result = call.jsonObject
    Log.i("TEST", "getUser: $result")
    user = User(
        name = result["name"]?.jsonPrimitive?.content ?: "",
        firstName = result["firstName"]?.jsonPrimitive?.content ?: "",
        lastName = result["lastName"]?.jsonPrimitive?.content ?: "",
        street = result.jsonObject["address"]?.jsonObject?.get("street")?.jsonPrimitive?.content
            ?: "",
        streetNumber = result.jsonObject["address"]?.jsonObject?.get("streetNumber")?.jsonPrimitive?.content
            ?: "",

        zipCode = result.jsonObject["address"]?.jsonObject?.get("zipCode")?.jsonPrimitive?.content
            ?: "",

        city = result.jsonObject["address"]?.jsonObject?.get("city")?.jsonPrimitive?.content
            ?: "",

        country = result.jsonObject["address"]?.jsonObject?.get("country")?.jsonPrimitive?.content
            ?: "",

        email = result["email"]?.jsonPrimitive?.content ?: ""
    )
    return user
}

suspend fun saveUser(token: String, user: User) {
    var body = buildJsonObject {
        put("name", JsonPrimitive(user.name))
        put("firstName", JsonPrimitive(user.firstName))
        put("lastName", JsonPrimitive(user.lastName))
        put("street", JsonPrimitive(user.street))
        put("streetNumber", JsonPrimitive(user.streetNumber))
        put("zipCode", JsonPrimitive(user.zipCode))
        put("city", JsonPrimitive(user.city))
        put("country", JsonPrimitive(user.country))
        put("email", JsonPrimitive(user.email))
    }
    try {
        val call: JsonObject = userApi.saveUser(token, body)
    } catch (e: Exception) {
        Log.e("TESTING", "UserLoginRequest: ", e)
    }
}
