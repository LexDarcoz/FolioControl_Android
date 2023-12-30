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

suspend fun fetchPremises(
    token: String,
    propertyID: Int,
): List<Premise> {
    var premises = propertyApi.getPremisesForProperty(token, propertyID)
    return premises
}

suspend fun fetchProperty(
    token: String,
    propertyID: Int,
): Property {
    var property = propertyApi.getProperty(token, propertyID)
    return property
}

suspend fun fetchDocuments(
    token: String,
    propertyID: Int,
): List<PropertyDocument> {
    return propertyApi.getDocumentsForProperty(token, propertyID)
}

suspend fun getUserPartnerships(token: String): List<Partnership> {
    return propertyApi.getUserPartnerships(token = token)
}

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
