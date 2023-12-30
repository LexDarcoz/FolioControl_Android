package foliocontrol.android.foliocontrolandroid.data.local.database

import foliocontrol.android.foliocontrolandroid.data.local.dao.PropertyDao
import foliocontrol.android.foliocontrolandroid.data.local.schema.PropertyRoomEntity
import foliocontrol.android.foliocontrolandroid.domain.Partnership
import kotlinx.coroutines.flow.Flow

/**
 * Implementation of [PropertyDatabase] using [PropertyDao] for database operations.
 *
 * @param propertyDao The DAO interface for property-related database operations.
 */
class PropertyDatabaseImpl(private val propertyDao: PropertyDao) : PropertyDatabase {
    /**
     * Retrieves properties associated with the active partnership as a Flow.
     *
     * @param partnership The active partnership for which properties are retrieved.
     * @return A [Flow] emitting a list of property entities associated with the active partnership.
     */
    override fun getPropertiesByActivePartnership(partnership: Partnership): Flow<List<PropertyRoomEntity>> {
        return propertyDao.getAllPropertiesFromCurrentPartnership(partnership.partnershipID)
    }

    /**
     * Retrieves all properties as a Flow.
     *
     * @return A [Flow] emitting a list of all property entities.
     */
    override fun getAllProperties(): Flow<List<PropertyRoomEntity>> {
        return propertyDao.getAllProperties()
    }

    /**
     * Inserts a list of property entities into the database.
     *
     * @param properties The list of property entities to be inserted.
     */
    override suspend fun insertAll(properties: List<PropertyRoomEntity>) {
        propertyDao.insertAll(properties)
    }

    /**
     * Drops the table associated with a specific partnership in the database.
     *
     * @param partnershipID The ID of the partnership for which the table is dropped.
     */
    override suspend fun dropTable(partnershipID: Int) {
        propertyDao.dropTable(partnershipID)
    }

    /**
     * Clears all tables related to properties in the database.
     */
    override suspend fun clearAllTables() {
        propertyDao.clearAllTables()
    }


}
