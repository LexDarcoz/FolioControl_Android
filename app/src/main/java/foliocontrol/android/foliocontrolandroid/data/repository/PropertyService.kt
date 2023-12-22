package foliocontrol.android.foliocontrolandroid.data.repository

import foliocontrol.android.foliocontrolandroid.domain.Partnership
import foliocontrol.android.foliocontrolandroid.domain.Premise
import foliocontrol.android.foliocontrolandroid.domain.Property
import foliocontrol.android.foliocontrolandroid.domain.PropertyDocument
import okhttp3.MultipartBody

interface PropertyService {
    suspend fun getProperties(token: String, partnership: Partnership): List<Property>
    suspend fun savePropertyByPropertyID(
        token: String, property: Property, propertyImage: MultipartBody.Part
    )

    suspend fun getDetailsForProperty(token: String, property: Property): Property

    suspend fun getDocumentsForProperty(token: String, property: Property): List<PropertyDocument>
    suspend fun getPremisesForProperty(token: String, property: Property): List<Premise>
    suspend fun addProperty(token: String, property: Property)
    suspend fun deletePropertyByPropertyID(token: String, propertyID: Int)
    suspend fun deleteDocumentByPropertyID(token: String, documentID: Int)
    suspend fun uploadDocument(
        token: String,
        documentImage: MultipartBody.Part, document: PropertyDocument,
    )


}
