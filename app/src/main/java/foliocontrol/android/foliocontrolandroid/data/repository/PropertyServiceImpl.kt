package foliocontrol.android.foliocontrolandroid.data.repository

import foliocontrol.android.foliocontrolandroid.data.remote.createProperty
import foliocontrol.android.foliocontrolandroid.data.remote.deletePropertyById
import foliocontrol.android.foliocontrolandroid.data.remote.fetchProperties
import foliocontrol.android.foliocontrolandroid.data.remote.savePropertyByID
import foliocontrol.android.foliocontrolandroid.domain.Partnership
import foliocontrol.android.foliocontrolandroid.domain.Property

class PropertyServiceImpl : PropertyService {
    override suspend fun getProperties(token: String, partnership: Partnership): List<Property> {
        return fetchProperties(token, partnership.partnershipID)!!
    }

    override suspend fun savePropertyByPropertyID(token: String, property: Property) {
        savePropertyByID(token, property)
    }

    override suspend fun addProperty(token: String, property: Property) {
        createProperty(token, property)
    }

    override suspend fun deletePropertyByPropertyID(token: String, propertyID: Int) {
        deletePropertyById(token, propertyID)
    }
}
