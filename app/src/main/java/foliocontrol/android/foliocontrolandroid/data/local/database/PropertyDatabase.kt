package foliocontrol.android.foliocontrolandroid.data.local.database

import foliocontrol.android.foliocontrolandroid.data.local.schema.PropertyRoomEntity
import foliocontrol.android.foliocontrolandroid.domain.Partnership
import kotlinx.coroutines.flow.Flow

/**
 * Interface defining the contract for property-related database operations.
 */
interface PropertyDatabase {
    /**
     * Retrieves properties associated with the active partnership as a Flow.
     *
     * @param partnership The active partnership for which properties are retrieved.
     * @return A [Flow] emitting a list of property entities associated with the active partnership.
     */
    fun getPropertiesByActivePartnership(partnership: Partnership): Flow<List<PropertyRoomEntity>>

    /**
     * Retrieves all properties as a Flow.
     *
     * @return A [Flow] emitting a list of all property entities.
     */
    fun getAllProperties(): Flow<List<PropertyRoomEntity>>

    /**
     * Inserts a list of property entities into the database.
     *
     * @param properties The list of property entities to be inserted.
     */
    suspend fun insertAll(properties: List<PropertyRoomEntity>)

    /**
     * Drops the table associated with a specific partnership in the database.
     *
     * @param partnershipID The ID of the partnership for which the table is dropped.
     */
    suspend fun dropTable(partnershipID: Int)

    /**
     * Clears all tables related to properties in the database.
     */
    suspend fun clearAllTables()
}
