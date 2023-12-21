package foliocontrol.android.foliocontrolandroid.data.remote

import android.util.Log
import foliocontrol.android.foliocontrolandroid.data.remote.common.createRetrofit
import foliocontrol.android.foliocontrolandroid.domain.Partnership
import foliocontrol.android.foliocontrolandroid.domain.Premise
import foliocontrol.android.foliocontrolandroid.domain.Property
import foliocontrol.android.foliocontrolandroid.domain.PropertyDocument
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.int
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive
import okhttp3.MultipartBody
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Part
import retrofit2.http.Path

interface PropertyAPI {
    @Headers("Accept: application/json")
    @GET("api/property/partnership/{partnershipID}")
    suspend fun getProperties(
        @Header("Authorization") token: String,
        @Path("partnershipID") partnershipID: Int
    ): JsonArray

    @Headers("Accept: application/json")
    @GET("api/user/getPartnerships")
    suspend fun getUserPartnerships(@Header("Authorization") token: String): JsonArray

    @Headers("Accept: application/json")
    @GET("api/propertyDocument/{propertyID}")
    suspend fun getDocumentsForProperty(
        @Header("Authorization") token: String,
        @Path("propertyID") propertyID: Int
    ): JsonArray

    @Headers("Accept: application/json")
    @GET("api/premises/property/{propertyID}")
    suspend fun getPremisesForProperty(
        @Header("Authorization") token: String,
        @Path("propertyID") propertyID: Int
    ): JsonArray

    @Headers("Accept: application/json")
    @Multipart
    @PUT("api/property/{propertyID}")
    suspend fun savePropertyByPropertyID(
        @Header("Authorization") token: String,
        @Path("propertyID") propertyID: Int,
        @Part propertyImage: MultipartBody.Part,
        @Body property: JsonObject
    )

    @Headers("Accept: application/json")
    @POST("api/property/create")
    suspend fun createProperty(
        @Header("Authorization") token: String,
        @Body property: JsonObject
    )

    @Headers("Accept: application/json")
    @DELETE("api/property/{propertyID}")
    suspend fun deletePropertyById(
        @Header("Authorization") token: String,
        @Path("propertyID") propertyID: Int
    )
}

private val propertyApi = createRetrofit(PropertyAPI::class.java)

suspend fun fetchProperties(token: String, partnershipID: Int): List<Property>? {
    var properties = propertyApi.getProperties(token, partnershipID)

    return properties.map {
        Property(
            it.jsonObject["propertyID"]?.jsonPrimitive?.int ?: 0,
            it.jsonObject["propertyName"]?.jsonPrimitive?.content ?: "",
            it.jsonObject["propertyType"]?.jsonPrimitive?.content ?: "",
            it.jsonObject["propertyImg"]?.jsonPrimitive?.content ?: "",
            it.jsonObject["street"]?.jsonPrimitive?.content ?: "",
            it.jsonObject["streetNumber"]?.jsonPrimitive?.content ?: "",
            it.jsonObject["city"]?.jsonPrimitive?.content ?: "",
            it.jsonObject["zipCode"]?.jsonPrimitive?.content ?: "",
            it.jsonObject["country"]?.jsonPrimitive?.content ?: "",
            it.jsonObject["propertyDescription"]?.jsonPrimitive?.content ?: "",
            it.jsonObject["FK_partnershipID"]?.jsonPrimitive?.int ?: 0
        )
    }
}

suspend fun fetchPremises(token: String, propertyID: Int): List<Premise>? {
    var premises = propertyApi.getPremisesForProperty(token, propertyID)

    Log.i("TEST", "fetchPremisesss: $premises")
    return premises.map {
        Premise(
            it.jsonObject["premisesID"]?.jsonPrimitive?.int ?: 0,
            it.jsonObject["premisesName"]?.jsonPrimitive?.content ?: "",
            it.jsonObject["bus"]?.jsonPrimitive?.content ?: "",
            it.jsonObject["rent"]?.jsonPrimitive?.content ?: "",
            it.jsonObject["img"]?.jsonPrimitive?.content ?: "",
            it.jsonObject["description"]?.jsonPrimitive?.content ?: "",
            it.jsonObject["address"]?.jsonPrimitive?.content ?: "",
            it.jsonObject["isActive"]?.jsonPrimitive?.int ?: 0,
            it.jsonObject["isRented"]?.jsonPrimitive?.int ?: 0,
            it.jsonObject["tenant"]?.jsonPrimitive?.content ?: "",
            it.jsonObject["FK_propertyID"]?.jsonPrimitive?.int ?: 0
        )
    }
}

suspend fun fetchDocuments(token: String, propertyID: Int): List<PropertyDocument>? {
    var documents = propertyApi.getDocumentsForProperty(token, propertyID)

    return documents.map {
        PropertyDocument(
            it.jsonObject["documentID"]?.jsonPrimitive?.int ?: 0,
            it.jsonObject["name"]?.jsonPrimitive?.content ?: "",
            it.jsonObject["documentName"]?.jsonPrimitive?.content ?: "",
            it.jsonObject["documentType"]?.jsonPrimitive?.content ?: "",
            it.jsonObject["expiryDate"]?.jsonPrimitive?.content ?: "",
            it.jsonObject["FK_propertyID"]?.jsonPrimitive?.int ?: 0
        )
    }
}

suspend fun getUserPartnerships(
    token: String
): List<Partnership> {
    val response: JsonArray = propertyApi.getUserPartnerships(token = token)
    return parseResponse(response)
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

    return partnerships
}

suspend fun savePropertyByID(
    token: String,
    property: Property,
    propertyImage: MultipartBody.Part
) {
    try {
        var body = buildJsonObject {
            put("propertyID", JsonPrimitive(property.propertyID))
            put("propertyName", JsonPrimitive(property.propertyName))
            put("propertyType", JsonPrimitive(property.propertyType))
            put("propertyImg", JsonPrimitive(property.propertyImg))
            put("street", JsonPrimitive(property.street))
            put("streetNumber", JsonPrimitive(property.streetNumber))
            put("city", JsonPrimitive(property.city))
            put("zipCode", JsonPrimitive(property.zipCode))
            put("country", JsonPrimitive(property.country))
            put("propertyDescription", JsonPrimitive(property.propertyDescription))
        }

        propertyApi.savePropertyByPropertyID(
            token,
            property.propertyID,
            propertyImage,
            body
        )
    } catch (e: Exception) {
        Log.e("TESTING", "savePropertyByPropertyID failed", e)
    }
}

suspend fun createProperty(token: String, property: Property) {
    Log.i("TEST", "createProperty: $property}")

    try {
        var body = buildJsonObject {
            put("propertyName", JsonPrimitive(property.propertyName))
            put("propertyType", JsonPrimitive(property.propertyType))
            put("propertyImg", JsonPrimitive(property.propertyImg))
            put("street", JsonPrimitive(property.street))
            put("streetNumber", JsonPrimitive(property.streetNumber))
            put("city", JsonPrimitive(property.city))
            put("zipCode", JsonPrimitive(property.zipCode))
            put("country", JsonPrimitive(property.country))
            put("propertyDescription", JsonPrimitive(property.propertyDescription))
            put("FK_partnershipID", JsonPrimitive(property.FK_partnershipID))
        }
        propertyApi.createProperty(token, body)
    } catch (e: Exception) {
        Log.e("TESTING", "createProperty failed", e)
    }
}

suspend fun deletePropertyById(token: String, propertyID: Int) {
    try {
        propertyApi.deletePropertyById(token, propertyID)
    } catch (e: Exception) {
        Log.e("TESTING", "deletePropertyById failed", e)
    }
}
