package foliocontrol.android.foliocontrolandroid.data.repository

import foliocontrol.android.foliocontrolandroid.domain.dataModels.Partnership
import foliocontrol.android.foliocontrolandroid.domain.dataModels.Property

interface PropertyService {

    suspend fun getProperties(token: String, partnership: Partnership): List<Property>
}
