package foliocontrol.android.foliocontrolandroid.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import foliocontrol.android.foliocontrolandroid.data.local.schema.PartnershipRoomEntity
import kotlinx.coroutines.flow.Flow

/**
 * Data Access Object (DAO) interface for the Partnership entity, providing methods to interact with the underlying database.
 */
@Dao
interface PartnershipDao {
    /**
     * Inserts a list of partnerships into the database. If there is a conflict with an existing entry, it replaces the existing entry.
     *
     * @param partnership List of [PartnershipRoomEntity] objects to be inserted.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllPartnerships(partnership: List<PartnershipRoomEntity>)

    /**
     * Retrieves all partnerships from the database and emits them as a Flow. This allows for reactive programming when the data changes.
     *
     * @return [Flow] emitting a list of [PartnershipRoomEntity] objects.
     */
    @Query("SELECT * FROM partnership_table")
    fun getPartnerships(): Flow<List<PartnershipRoomEntity>>

    /**
     * Clears all entries in the partnership table.
     */
    @Query("DELETE FROM partnership_table")
    suspend fun clearAllTables()
}
