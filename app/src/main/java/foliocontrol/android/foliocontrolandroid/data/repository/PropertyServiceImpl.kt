package foliocontrol.android.foliocontrolandroid.data.repository

import foliocontrol.android.foliocontrolandroid.data.remote.createProperty
import foliocontrol.android.foliocontrolandroid.data.remote.deletePropertyById
import foliocontrol.android.foliocontrolandroid.data.remote.fetchDocuments
import foliocontrol.android.foliocontrolandroid.data.remote.fetchPremises
import foliocontrol.android.foliocontrolandroid.data.remote.fetchProperties
import foliocontrol.android.foliocontrolandroid.data.remote.savePropertyByID
import foliocontrol.android.foliocontrolandroid.domain.Partnership
import foliocontrol.android.foliocontrolandroid.domain.Premise
import foliocontrol.android.foliocontrolandroid.domain.Property
import foliocontrol.android.foliocontrolandroid.domain.PropertyDocument
import okhttp3.MultipartBody

class PropertyServiceImpl : PropertyService {
    override suspend fun getProperties(token: String, partnership: Partnership): List<Property> {
        return fetchProperties(token, partnership.partnershipID)!!
    }

    override suspend fun savePropertyByPropertyID(
        token: String,
        property: Property,
        propertyImage: MultipartBody.Part
    ) {
        savePropertyByID(token, property, propertyImage)
    }

    override suspend fun getDocumentsForProperty(
        token: String,
        property: Property
    ): List<PropertyDocument> {
        return fetchDocuments(token, property.propertyID)!!
    }

    override suspend fun getPremisesForProperty(token: String, property: Property): List<Premise> {
        return fetchPremises(token, property.propertyID)!!
    }

    override suspend fun addProperty(token: String, property: Property) {
        createProperty(token, property)
    }

    override suspend fun deletePropertyByPropertyID(token: String, propertyID: Int) {
        deletePropertyById(token, propertyID)
    }
}
