package foliocontrol.android.foliocontrolandroid.data.remote.common

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

object Constants {
    const val BASE_URL = "https://api.schattemanit.com/"
    const val PROPERTYPHOTOS_URL = "https://api.schattemanit.com/propertyImages"
    const val PROPERTYDOCUMENTS_URL = "https://api.schattemanit.com/propertyDocuments"
    const val PREMISESPHOTOS_URL = "https://api.schattemanit.com/propertyphotos/"
    const val PREMISESDOCUMENTS_URL = "https://api.schattemanit.com/premisesDocuments"
}

fun <T> createRetrofit(apiClass: Class<T>): T {


    val retrofit = Retrofit.Builder().baseUrl(Constants.BASE_URL)
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType())).build()

    return retrofit.create(apiClass)
}



