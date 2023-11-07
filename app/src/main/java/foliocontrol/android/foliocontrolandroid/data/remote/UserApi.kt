package foliocontrol.android.foliocontrolandroid.data.remote

import android.util.Log
import foliocontrol.android.foliocontrolandroid.data.remote.common.createRetrofit
import foliocontrol.android.foliocontrolandroid.domain.dataModels.LoginState
import foliocontrol.android.foliocontrolandroid.domain.dataModels.Partnership
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.int
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

    @Headers("Accept: application/json")
    @GET("api/user/getPartnerships")
    suspend fun getUserPartnerships(@Header("Authorization") token: String): JsonArray
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

suspend fun getUserPartnerships(
    token: String
): List<Partnership> {
    try {
        val response: JsonArray = userApi.getUserPartnerships(token = token)
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
