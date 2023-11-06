package foliocontrol.android.foliocontrolandroid.data.remote

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import foliocontrol.android.foliocontrolandroid.data.Property
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Header

interface PropertyAPI {
    @GET("api/property/partnership") // Change the endpoint as needed
    suspend fun getProperties(@Header("Authorization") partnershipID: Int): List<Property>
}

fun createRetrofit(): PropertyAPI {
    val retrofit = Retrofit.Builder().baseUrl("http://10.0.2.2:9000")
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType())).build()

    return retrofit.create(PropertyAPI::class.java)
}

suspend fun fetchProperties(partnershipID: Int): List<Property>? {
    val api = createRetrofit()

    try {
        return api.getProperties(partnershipID)
    } catch (e: Exception) {
        e.printStackTrace()
        return null
    }
}
