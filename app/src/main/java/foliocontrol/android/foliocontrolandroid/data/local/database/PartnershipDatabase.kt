package foliocontrol.android.foliocontrolandroid.data.local.database

import foliocontrol.android.foliocontrolandroid.data.local.schema.PartnershipRoomEntity
import kotlinx.coroutines.flow.Flow

/**
 * Interface defining database operations for partnerships.
 */
interface PartnershipDatabase {
    /**
     * Retrieves all partnerships as a Flow by delegating to the corresponding DAO method.
     *
     * @return A [Flow] emitting a list of partnerships.
     */
    fun getPartnerships(): Flow<List<PartnershipRoomEntity>>

    /**
     * Inserts a list of partnerships into the database by delegating to the corresponding DAO method.
     *
     * @param partnerships The list of partnership entities to be inserted.
     */
    suspend fun insertAllPartnerships(partnerships: List<PartnershipRoomEntity>)

    /**
     * Clears all tables related to partnerships in the database by delegating to the corresponding DAO method.
     */
    suspend fun clearAllTables()
}
