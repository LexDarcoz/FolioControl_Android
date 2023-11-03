package foliocontrol.android.foliocontrolandroid.api

import android.util.Log
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import foliocontrol.android.foliocontrolandroid.data.LoginState
import foliocontrol.android.foliocontrolandroid.data.Partnership
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
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

    @GET("api/user/getPartnerships")
    suspend fun getUserPartnerships(@Header("Authorization") token: String): JsonObject
}

suspend fun UserLoginRequest(
    loginCredentials: LoginState,
    updateTokenState: (String) -> Unit
): Boolean {
    val retrofit = Retrofit.Builder().baseUrl("http://10.0.2.2:9000")
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType())).build()
    val api = retrofit.create(UserApi::class.java)
    var mapper: Map<String, JsonElement> = emptyMap()
    mapper = mapper.plus(Pair("email", JsonPrimitive(loginCredentials.email)))
    mapper = mapper.plus(Pair("password", JsonPrimitive(loginCredentials.password)))

    var obj = JsonObject(content = mapper)
    Log.i("UserLoginRequest", "obj: $obj")
    val call: JsonObject = api.login(obj)
    Log.i("UserLoginRequest", "call: $call")
    var token = call["token"].toString()
    updateTokenState(token)

    return token.isNotEmpty()
}

suspend fun getUserPartnerships(
    token: String
): List<Partnership> {
    Log.i("TESTING", "getUserPartnerships: $token")
    val retrofit = Retrofit.Builder()
        .baseUrl("http://10.0.2.2:9000")
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        .build()

    val api = retrofit.create(UserApi::class.java)
    val response =
        api.getUserPartnerships(
            "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJuYW1lIjoiV291dGVyIiwiZW1haWwiOiJXb3V0ZXJAZ21haWwuY29tIiwidXNlcklEIjoiOTVlMzc0NWUtN2E4ZC00OTQ3LTlmZjEtNTlmNzY1NDQ1NjRiIiwiaWF0IjoxNjk5MDIzMDgwLCJleHAiOjE2OTkwNTkwODAsImF1ZCI6Imh0dHA6Ly9sb2NhbGhvc3Q6OTAwMCIsImlzcyI6Imh0dHA6Ly9sb2NhbGhvc3Q6MzAwMCJ9.H6uY_REb3yfNoRJZAlV0SrVH7_mcCV8aHLlrSVesdgg"
        )
    Log.i("TESTING", "getUserPartnerships: $response")
    return emptyList()
}
