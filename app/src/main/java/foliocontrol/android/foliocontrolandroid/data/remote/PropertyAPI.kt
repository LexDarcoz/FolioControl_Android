package foliocontrol.android.foliocontrolandroid.data.remote

import android.util.Log
import foliocontrol.android.foliocontrolandroid.data.remote.common.createRetrofit
import foliocontrol.android.foliocontrolandroid.domain.Partnership
import foliocontrol.android.foliocontrolandroid.domain.Premise
import foliocontrol.android.foliocontrolandroid.domain.Property
import foliocontrol.android.foliocontrolandroid.domain.PropertyDocument
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

/**
 * Retrofit API interface for handling property-related requests.
 */
interface PropertyAPI {
    @Headers("Accept: application/json")
    @GET("api/property/partnership/{partnershipID}")
    suspend fun getProperties(
        @Header("Authorization") token: String,
        @Path("partnershipID") partnershipID: Int,
    ): List<Property>

    @Headers("Accept: application/json")
    @GET("api/user/getPartnerships")
    suspend fun getUserPartnerships(
        @Header("Authorization") token: String,
    ): List<Partnership>

    @Headers("Accept: application/json")
    @GET("api/propertyDocument/{propertyID}")
    suspend fun getDocumentsForProperty(
        @Header("Authorization") token: String,
        @Path("propertyID") propertyID: Int,
    ): List<PropertyDocument>

    @Headers("Accept: application/json")
    @GET("api/premises/property/{propertyID}")
    suspend fun getPremisesForProperty(
        @Header("Authorization") token: String,
        @Path("propertyID") propertyID: Int,
    ): List<Premise>

    @Headers("Accept: application/json")
    @Multipart
    @PUT("api/property/{propertyID}")
    suspend fun savePropertyByPropertyID(
        @Header("Authorization") token: String,
        @Path("propertyID") propertyID: Int,
        @Part propertyImage: MultipartBody.Part,
        @Part("property") property: Property,
    )

    @Headers("Accept: application/json")
    @Multipart
    @POST("api/propertyDocument/create")
    suspend fun uploadDocumentByPropertyID(
        @Header("Authorization") token: String,
        @Part("property") property: PropertyDocument,
        @Part document: MultipartBody.Part,
    )

    @Headers("Accept: application/json")
    @POST("api/property/create")
    suspend fun createProperty(
        @Header("Authorization") token: String,
        @Body property: Property,
    )

    @Headers("Accept: application/json")
    @GET("api/property/{propertyID}")
    suspend fun getProperty(
        @Header("Authorization") token: String,
        @Path("propertyID") propertyID: Int,
    ): Property

    @Headers("Accept: application/json")
    @DELETE("api/property/{propertyID}")
    suspend fun deletePropertyById(
        @Header("Authorization") token: String,
        @Path("propertyID") propertyID: Int,
    )

    @Headers("Accept: application/json")
    @DELETE("api/propertyDocument/delete/{documentID}")
    suspend fun deleteDocumentById(
        @Header("Authorization") token: String,
        @Path("documentID") documentID: Int,
    )
}

private val propertyApi = createRetrofit(PropertyAPI::class.java)

/**
 * Function to fetch a list of properties for a given partnership ID.
 *
 * @param token The authorization token.
 * @param partnershipID The ID of the partnership.
 * @return List of [Property] objects.
 */
suspend fun fetchProperties(
    token: String,
    partnershipID: Int,
): List<Property> {
    var properties = emptyList<Property>()
    try {
        properties = propertyApi.getProperties(token, partnershipID)
    } catch (e: Exception) {
        Log.e("TEST", "fetchProperties: ${e.message}")
    }
    return properties
}

/**
 * Function to fetch a list of premises for a given property ID.
 *
 * @param token The authorization token.
 * @param propertyID The ID of the property.
 * @return List of [Premise] objects.
 */
suspend fun fetchPremises(
    token: String,
    propertyID: Int,
): List<Premise> {
    var premises = propertyApi.getPremisesForProperty(token, propertyID)
    return premises
}

/**
 * Function to fetch details for a given property ID.
 *
 * @param token The authorization token.
 * @param propertyID The ID of the property.
 * @return [Property] object.
 */
suspend fun fetchProperty(
    token: String,
    propertyID: Int,
): Property {
    var property = propertyApi.getProperty(token, propertyID)
    return property
}

/**
 * Function to fetch a list of documents for a given property ID.
 *
 * @param token The authorization token.
 * @param propertyID The ID of the property.
 * @return List of [PropertyDocument] objects.
 */
suspend fun fetchDocuments(
    token: String,
    propertyID: Int,
): List<PropertyDocument> {
    return propertyApi.getDocumentsForProperty(token, propertyID)
}

/**
 * Function to get partnerships for the logged-in user.
 *
 * @param token The authorization token.
 * @return List of [Partnership] objects.
 */
suspend fun getUserPartnerships(token: String): List<Partnership> {
    return propertyApi.getUserPartnerships(token = token)
}

/**
 * Function to save a property by its ID.
 *
 * @param token The authorization token.
 * @param property The [Property] to save.
 * @param propertyImage The image of the property.
 */
suspend fun savePropertyByID(
    token: String,
    property: Property,
    propertyImage: MultipartBody.Part,
) {
    try {
        propertyApi.savePropertyByPropertyID(
            token,
            property.propertyID,
            propertyImage,
            property,
        )
    } catch (e: Exception) {
        Log.e("TESTING", "savePropertyByPropertyID failed", e)
    }
}

/**
 * Function to upload a document for a property.
 *
 * @param token The authorization token.
 * @param documentFile The document file to upload.
 * @param document The [PropertyDocument] object.
 */
suspend fun uploadDocumentByPropertyID(
    token: String,
    documentFile: MultipartBody.Part,
    document: PropertyDocument,
) {
    try {
        propertyApi.uploadDocumentByPropertyID(
            token,
            document,
            documentFile,
        )
    } catch (e: Exception) {
        Log.e("TESTING", "uploadDocumentByPropertyID failed", e)
    }
}

/**
 * Function to create a property.
 *
 * @param token The authorization token.
 * @param property The [Property] to create.
 */
suspend fun createProperty(
    token: String,
    property: Property,
) {
    try {
        propertyApi.createProperty(token, property)
    } catch (e: Exception) {
        Log.e("TESTING", "createProperty failed", e)
    }
}

/**
 * Function to delete a property by its ID.
 *
 * @param token The authorization token.
 * @param propertyID The ID of the property to delete.
 */
suspend fun deletePropertyById(
    token: String,
    propertyID: Int,
) {
    try {
        propertyApi.deletePropertyById(token, propertyID)
    } catch (e: Exception) {
        Log.e("TESTING", "deletePropertyById failed", e)
    }
}

/**
 * Function to delete a document by its ID.
 *
 * @param token The authorization token.
 * @param documentID The ID of the document to delete.
 */
suspend fun deleteDocumentByID(
    token: String,
    documentID: Int,
) {
    try {
        propertyApi.deleteDocumentById(token, documentID)
    } catch (e: Exception) {
        Log.e("TESTING", "deleteDocumentById failed", e)
    }
}
