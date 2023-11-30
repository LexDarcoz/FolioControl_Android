package foliocontrol.android.foliocontrolandroid.data.local.database

import foliocontrol.android.foliocontrolandroid.data.local.schema.PropertyRoomEntity

interface PropertyDatabase {
    suspend fun getPropertyByPropertyNumber(propertyNumber: String): PropertyRoomEntity
    suspend fun getPropertiesByActivePartnership(partnershipID: String): List<PropertyRoomEntity>

}