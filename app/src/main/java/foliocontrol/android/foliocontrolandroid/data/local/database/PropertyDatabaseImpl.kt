package foliocontrol.android.foliocontrolandroid.data.local.database

import androidx.room.Dao
import foliocontrol.android.foliocontrolandroid.data.local.dao.PropertyDao
import foliocontrol.android.foliocontrolandroid.data.local.schema.PropertyRoomEntity

class PropertyDatabaseImpl(private val propertyDao: PropertyDao) : PropertyDatabase {
    override suspend fun getPropertyByPropertyNumber(propertyNumber: String): PropertyRoomEntity {
        TODO("Not yet implemented")
    }

    override suspend fun getPropertiesByActivePartnership(partnershipID: String): List<PropertyRoomEntity> {
        TODO("Not yet implemented")
    }


}