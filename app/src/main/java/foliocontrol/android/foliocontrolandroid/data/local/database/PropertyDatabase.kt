package foliocontrol.android.foliocontrolandroid.data.local.database

import foliocontrol.android.foliocontrolandroid.domain.Partnership
import foliocontrol.android.foliocontrolandroid.domain.Property

interface PropertyDatabase {
    suspend fun getPropertiesByActivePartnership(partnership: Partnership): List<Property>
}
