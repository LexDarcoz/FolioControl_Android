package foliocontrol.android.foliocontrolandroid.data.remote.common

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Apartment
import androidx.compose.material.icons.filled.Garage
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.House
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Store
import androidx.compose.material.icons.filled.Villa
import androidx.compose.material.icons.filled.Warehouse
import androidx.compose.ui.graphics.vector.ImageVector
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

object Constants {

    // ONLINE
    const val BASE_URL = "https://api.schattemanit.com/"
    const val PROPERTYPHOTOS_URL = "https://api.schattemanit.com/propertyImages"
    const val PROPERTYDOCUMENTS_URL = "https://api.schattemanit.com/propertyDocuments"
    const val PREMISESPHOTOS_URL = "https://api.schattemanit.com/propertyphotos/"
    const val PREMISESDOCUMENTS_URL = "https://api.schattemanit.com/premisesDocuments"
    // OFFLINE
//    const val BASE_URL = "http://10.0.2.2:9000"
//    const val PROPERTYPHOTOS_URL = "http://10.0.2.2:9000/propertyImages"
//    const val PROPERTYDOCUMENTS_URL = "http://10.0.2.2:9000/propertyDocuments"
//    const val PREMISESPHOTOS_URL = "http://10.0.2.2:9000/propertyphotos/"
//    const val PREMISESDOCUMENTS_URL = "http://10.0.2.2:9000/premisesDocuments"

    val propertyTypesIcons: Map<String, ImageVector> = mapOf(
        "Apartment" to Icons.Default.Apartment,
        "House" to Icons.Default.House,
        "Garage" to Icons.Default.Garage,
        "Store" to Icons.Default.Store,
        "Terraced House" to Icons.Default.Villa,
        "Semi-detached" to Icons.Default.Home,
        "Villa" to Icons.Default.Villa,
        "Storage" to Icons.Default.Warehouse,
        "Other" to Icons.Default.Info

    )
}

fun <T> createRetrofit(apiClass: Class<T>): T {
    val retrofit = Retrofit.Builder().baseUrl(Constants.BASE_URL)
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType())).build()

    return retrofit.create(apiClass)
}
