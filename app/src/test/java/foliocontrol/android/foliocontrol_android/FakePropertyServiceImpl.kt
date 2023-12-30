package foliocontrol.android.foliocontrol_android

import foliocontrol.android.foliocontrolandroid.data.repository.PropertyService
import foliocontrol.android.foliocontrolandroid.domain.Partnership
import foliocontrol.android.foliocontrolandroid.domain.Premise
import foliocontrol.android.foliocontrolandroid.domain.Property
import foliocontrol.android.foliocontrolandroid.domain.PropertyDocument
import okhttp3.MultipartBody

class FakePropertyServiceImpl : PropertyService {
    override suspend fun getProperties(token: String, partnership: Partnership): List<Property> {
        TODO("Not yet implemented")
    }

    override suspend fun savePropertyByPropertyID(
        token: String, property: Property, propertyImage: MultipartBody.Part
    ) {
        TODO("Not yet implemented")
    }

    override suspend fun getDetailsForProperty(token: String, property: Property): Property {
        TODO("Not yet implemented")
    }

    override suspend fun getDocumentsForProperty(
        token: String, property: Property
    ): List<PropertyDocument> {
        TODO("Not yet implemented")
    }

    override suspend fun getPremisesForProperty(token: String, property: Property): List<Premise> {
        TODO("Not yet implemented")
    }

    override suspend fun addProperty(token: String, property: Property) {
        TODO("Not yet implemented")
    }

    override suspend fun deletePropertyByPropertyID(token: String, propertyID: Int) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteDocumentByPropertyID(token: String, documentID: Int) {
        TODO("Not yet implemented")
    }

    override suspend fun uploadDocument(
        token: String, documentImage: MultipartBody.Part, document: PropertyDocument
    ) {
        TODO("Not yet implemented")
    }
}