package foliocontrol.android.foliocontrolandroid.data.repository

import foliocontrol.android.foliocontrolandroid.data.remote.fetchProperties
import foliocontrol.android.foliocontrolandroid.data.remote.savePropertyByID
import foliocontrol.android.foliocontrolandroid.domain.dataModels.Partnership
import foliocontrol.android.foliocontrolandroid.domain.dataModels.Property

class PropertyServiceImpl : PropertyService {
    override suspend fun getProperties(token: String, partnership: Partnership): List<Property> {
        var properties = fetchProperties(token, partnership.partnershipID) ?: emptyList()
        return properties
    }

    override suspend fun savePropertyByPropertyID(token: String, property: Property) {
        savePropertyByID(token, property)
    }
}
