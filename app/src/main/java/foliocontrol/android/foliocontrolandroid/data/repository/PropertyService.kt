package foliocontrol.android.foliocontrolandroid.data.repository

import foliocontrol.android.foliocontrolandroid.domain.Partnership
import foliocontrol.android.foliocontrolandroid.domain.Property

interface PropertyService {

    suspend fun getProperties(token: String, partnership: Partnership): List<Property>
    suspend fun savePropertyByPropertyID(token: String, property: Property)
}
