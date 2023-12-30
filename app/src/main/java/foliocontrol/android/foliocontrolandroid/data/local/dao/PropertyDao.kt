package foliocontrol.android.foliocontrolandroid.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import foliocontrol.android.foliocontrolandroid.data.local.schema.PropertyRoomEntity
import kotlinx.coroutines.flow.Flow

/**
 * Data Access Object (DAO) interface for the Property entity, providing methods to interact with the underlying database.
 */
@Dao
interface PropertyDao {
    /**
     * Inserts a single property into the database. If there is a conflict with an existing entry, it replaces the existing entry.
     *
     * @param property The [PropertyRoomEntity] object to be inserted.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(property: PropertyRoomEntity)

    /**
     * Inserts a list of properties into the database. If there is a conflict with an existing entry, it replaces the existing entry.
     *
     * @param properties List of [PropertyRoomEntity] objects to be inserted.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(properties: List<PropertyRoomEntity>)

    /**
     * Retrieves all properties from the database and emits them as a Flow. This allows for reactive programming when the data changes.
     *
     * @return [Flow] emitting a list of [PropertyRoomEntity] objects.
     */
    @Query("SELECT * FROM property_table")
    fun getAllProperties(): Flow<List<PropertyRoomEntity>>

    /**
     * Retrieves all properties associated with a specific partnership from the database and emits them as a Flow. This allows for reactive programming when the data changes.
     *
     * @param partnershipId The ID of the partnership for which properties are retrieved.
     * @return [Flow] emitting a list of [PropertyRoomEntity] objects associated with the specified partnership.
     */
    @Query("SELECT * FROM property_table where FK_partnership_id = :partnershipId")
    fun getAllPropertiesFromCurrentPartnership(partnershipId: Int): Flow<List<PropertyRoomEntity>>

    /**
     * Deletes all entries in the property table associated with a specific partnership.
     *
     * @param partnershipID The ID of the partnership for which the property entries should be deleted.
     */
    @Query("DELETE FROM property_table where FK_partnership_id = :partnershipID")
    suspend fun dropTable(partnershipID: Int)

    /**
     * Clears all entries in the property table.
     */
    @Query("DELETE FROM property_table")
    suspend fun clearAllTables()
}
