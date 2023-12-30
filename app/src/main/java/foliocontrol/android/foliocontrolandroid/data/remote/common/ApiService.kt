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
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Object holding constant values used throughout the application.
 */
object Constants {
    // ONLINE
    const val BASE_URL = "https://api.schattemanit.com/"
    const val PROPERTYPHOTOS_URL = "https://api.schattemanit.com/propertyImages"
    const val PROPERTYDOCUMENTS_URL = "https://api.schattemanit.com/propertyDocuments"
    // OFFLINE
//    const val BASE_URL = "http://10.0.2.2:9000"
//    const val PROPERTYPHOTOS_URL = "http://10.0.2.2:9000/propertyImages"
//    const val PROPERTYDOCUMENTS_URL = "http://10.0.2.2:9000/propertyDocuments"

    /**
     * Map associating property types with corresponding [ImageVector] icons from the [Icons.Default] set.
     */
    val propertyTypesIcons: Map<String, ImageVector> = mapOf(
        "Apartment" to Icons.Default.Apartment,
        "House" to Icons.Default.House,
        "Garage" to Icons.Default.Garage,
        "Store" to Icons.Default.Store,
        "Terraced House" to Icons.Default.Villa,
        "Semi-detached" to Icons.Default.Home,
        "Villa" to Icons.Default.Villa,
        "Storage" to Icons.Default.Warehouse,
        "Other" to Icons.Default.Info,
    )
}

/**
 * Function to create a Retrofit instance for the specified API class.
 *
 * @param apiClass The class of the API to create.
 * @return Instance of the specified API class with a Retrofit client.
 */
fun <T> createRetrofit(apiClass: Class<T>): T {
    val retrofit = Retrofit.Builder().baseUrl(Constants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create()).build()

    return retrofit.create(apiClass)
}
