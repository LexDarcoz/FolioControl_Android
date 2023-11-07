package foliocontrol.android.foliocontrolandroid.data.remote

import android.util.Log
import foliocontrol.android.foliocontrolandroid.data.remote.common.createRetrofit
import foliocontrol.android.foliocontrolandroid.domain.dataModels.LoginState
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
    suspend fun getUser(@Header("Authorization") token: JsonObject): JsonObject
}

private val userApi = createRetrofit(UserApi::class.java)
suspend fun UserLoginRequest(
    loginCredentials: LoginState,
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
