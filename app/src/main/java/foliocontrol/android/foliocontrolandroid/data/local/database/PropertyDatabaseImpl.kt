package foliocontrol.android.foliocontrolandroid.data.local.database

import foliocontrol.android.foliocontrolandroid.data.local.dao.PropertyDao
import foliocontrol.android.foliocontrolandroid.data.local.schema.PartnershipRoomEntity
import foliocontrol.android.foliocontrolandroid.data.local.schema.PropertyRoomEntity
import foliocontrol.android.foliocontrolandroid.domain.Partnership
import kotlinx.coroutines.flow.Flow

class PropertyDatabaseImpl(private val propertyDao: PropertyDao) : PropertyDatabase {
    override fun getPropertiesByActivePartnership(partnership: Partnership): Flow<List<PropertyRoomEntity>> {
        return propertyDao.getAllPropertiesFromCurrentPartnership(partnership.partnershipID)
    }


    override fun getAllProperties(): Flow<List<PropertyRoomEntity>> {
        return propertyDao.getAllProperties()
    }
    override suspend fun dropTable(partnershipID : Int) {
        propertyDao.dropTable(partnershipID)
    }


    override suspend fun insertAll(properties: List<PropertyRoomEntity>) {
        propertyDao.insertAll(properties)
    }

}
