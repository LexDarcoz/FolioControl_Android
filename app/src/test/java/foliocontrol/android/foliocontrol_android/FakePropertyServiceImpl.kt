package foliocontrol.android.foliocontrol_android

import foliocontrol.android.foliocontrolandroid.data.repository.PropertyService
import foliocontrol.android.foliocontrolandroid.domain.Partnership
import foliocontrol.android.foliocontrolandroid.domain.Premise
import foliocontrol.android.foliocontrolandroid.domain.Property
import foliocontrol.android.foliocontrolandroid.domain.PropertyDocument
import okhttp3.MultipartBody

class FakePropertyServiceImpl : PropertyService {

    var properties = listOf(
        Property(
            1,
            "Test Property",
            "Test type",
            "Test img",
            "Test street",
            "Test streetnumb",
            "Test city",
            "Test zip",
            "Test country",
            "Test size",
            "Test price",
            "Test desc",
            0,
        ),
        Property(
            2,
            "Test Property 2",
            "Test type 2",
            "Test img 2",
            "Test street 2",
            "Test streetnumb 2",
            "Test city 2",
            "Test zip 2",
            "Test country 2",
            "Test size 2",
            "Test price 2",
            "Test desc 2",
            0,
        ),
    )


    override suspend fun getProperties(
        token: String, partnership: Partnership
    ): List<Property> {
        if (partnership.partnershipID == 1) {
            return properties
        }
        return listOf()
    }

    override suspend fun savePropertyByPropertyID(
        token: String, property: Property, propertyImage: MultipartBody.Part
    ) {
        properties = properties + property
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
        properties = properties.filter { it.propertyID != propertyID }
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