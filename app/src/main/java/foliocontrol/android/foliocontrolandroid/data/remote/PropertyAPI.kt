package foliocontrol.android.foliocontrolandroid.data.remote

import foliocontrol.android.foliocontrolandroid.data.remote.common.createRetrofit
import foliocontrol.android.foliocontrolandroid.domain.dataModels.Property
import retrofit2.http.GET
import retrofit2.http.Header

interface PropertyAPI {
    @GET("api/property/partnership") // Change the endpoint as needed
    suspend fun getProperties(@Header("Authorization") partnershipID: Int): List<Property>
}

private val propertyApi = createRetrofit(PropertyAPI::class.java)

suspend fun fetchProperties(partnershipID: Int): List<Property>? {
    try {
        return propertyApi.getProperties(partnershipID)
    } catch (e: Exception) {
        e.printStackTrace()
        return null
    }
}
