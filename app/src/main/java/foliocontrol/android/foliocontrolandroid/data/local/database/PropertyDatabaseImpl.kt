package foliocontrol.android.foliocontrolandroid.data.local.database

import foliocontrol.android.foliocontrolandroid.data.local.dao.PropertyDao
import foliocontrol.android.foliocontrolandroid.data.local.schema.PropertyRoomEntity
import foliocontrol.android.foliocontrolandroid.domain.Partnership
import kotlinx.coroutines.flow.Flow

class PropertyDatabaseImpl(private val propertyDao: PropertyDao) : PropertyDatabase {
    override fun getPropertiesByActivePartnership(partnership: Partnership): Flow<List<PropertyRoomEntity>> {
        return propertyDao.getAllProperties()
    }

    override suspend fun insertAll(properties: List<PropertyRoomEntity>) {
        propertyDao.insertAll(properties)
    }
}
