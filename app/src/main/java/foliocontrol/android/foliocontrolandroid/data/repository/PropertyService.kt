package foliocontrol.android.foliocontrolandroid.data.repository

import foliocontrol.android.foliocontrolandroid.domain.Partnership
import foliocontrol.android.foliocontrolandroid.domain.Premise
import foliocontrol.android.foliocontrolandroid.domain.Property
import foliocontrol.android.foliocontrolandroid.domain.PropertyDocument

interface PropertyService {
    suspend fun getProperties(token: String, partnership: Partnership): List<Property>
    suspend fun savePropertyByPropertyID(token: String, property: Property)
    suspend fun getDocumentsForProperty(token: String, property: Property): List<PropertyDocument>
    suspend fun getPremisesForProperty(token: String, property: Property): List<Premise>
    suspend fun addProperty(token: String, property: Property)
    suspend fun deletePropertyByPropertyID(token: String, propertyID: Int)
}
