package foliocontrol.android.foliocontrolandroid.data.remote

import android.util.Log
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import foliocontrol.android.foliocontrolandroid.data.LoginState
import foliocontrol.android.foliocontrolandroid.data.Partnership
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.int
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive
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

    @Headers("Accept: application/json")
    @GET("api/user/getPartnerships")
    suspend fun getUserPartnerships(@Header("Authorization") token: String): JsonArray
}

suspend fun UserLoginRequest(
    loginCredentials: LoginState,
    updateTokenState: (String) -> Unit
): Boolean {
    val retrofit = Retrofit.Builder().baseUrl("http://10.0.2.2:9000")
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType())).build()
    val api = retrofit.create(UserApi::class.java)

    var body = buildJsonObject {
        put("email", JsonPrimitive(loginCredentials.email))
        put("password", JsonPrimitive(loginCredentials.password))
    }
    val call: JsonObject = api.login(body)
    val token = call.jsonObject["token"]?.jsonPrimitive?.content
    return if (token != null) {
        updateTokenState(token)
        true
    } else {
        false
    }
}

suspend fun getUserPartnerships(
    token: String
): List<Partnership> {
    val retrofit = Retrofit.Builder().baseUrl("http://10.0.2.2:9000")
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType())).build()
    val api = retrofit.create(UserApi::class.java)
    try {
        val response: JsonArray = api.getUserPartnerships(token = token)
        return parseResponse(response)
    } catch (e: Exception) {
        // Handle exceptions here if the network request fails
        Log.e("TESTING", "getUserPartnerships failed", e)
    }

    return emptyList()
}

fun parseResponse(response: JsonArray): List<Partnership> {
    val partnerships = mutableListOf<Partnership>()

    response.forEach { element ->
        if (element is JsonObject) {
            val partnershipID = element["partnershipID"]
            val name = element["name"]
            val logoImg = element["logoImg"]
            val countryCode = element["countryCode"]
            val vatNumber = element["vatNumber"]
            val street = element["street"]
            val streetNumber = element["streetNumber"]
            val zipCode = element["zipCode"]
            val city = element["city"]
            val country = element["country"]

            if (name != null && vatNumber != null) {
                Log.i("TESTING", "parseResponse: $name")
                val partnership = Partnership(
                    partnershipID = partnershipID?.jsonPrimitive?.int ?: 0,
                    name = name.jsonPrimitive.content,
                    logoImg = logoImg?.jsonPrimitive?.content ?: "",
                    countryCode = countryCode?.jsonPrimitive?.content ?: "",
                    vatNumber = vatNumber.jsonPrimitive.content,
                    street = street?.jsonPrimitive?.content ?: "",
                    streetNumber = streetNumber?.jsonPrimitive?.content ?: "",
                    zipCode = zipCode?.jsonPrimitive?.content ?: "",
                    city = city?.jsonPrimitive?.content ?: "",
                    country = country?.jsonPrimitive?.content ?: ""
                )
                partnerships.add(partnership)
            }
        }
    }
    Log.i("TESTING", "parseResponse: $partnerships")
    return partnerships
}
