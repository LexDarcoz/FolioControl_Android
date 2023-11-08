package foliocontrol.android.foliocontrolandroid.data.remote.common

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

fun <T> createRetrofit(apiClass: Class<T>): T  {
    val retrofit = Retrofit.Builder().baseUrl("http://10.0.2.2:9000")
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType())).build()

    return retrofit.create(apiClass)
}
