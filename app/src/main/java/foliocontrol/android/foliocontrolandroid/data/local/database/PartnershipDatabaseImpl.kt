package foliocontrol.android.foliocontrolandroid.data.local.database

import foliocontrol.android.foliocontrolandroid.data.local.dao.PartnershipDao
import foliocontrol.android.foliocontrolandroid.data.local.schema.PartnershipRoomEntity
import kotlinx.coroutines.flow.Flow

/**
 * Implementation of [PartnershipDatabase] interface.
 *
 * @property partnershipDao The DAO providing access to partnership-related database operations.
 */
class PartnershipDatabaseImpl(private val partnershipDao: PartnershipDao) : PartnershipDatabase {
    /**
     * Retrieves all partnerships as a Flow by delegating to the corresponding DAO method.
     *
     * @return A [Flow] emitting a list of partnerships.
     */
    override fun getPartnerships(): Flow<List<PartnershipRoomEntity>> {
        return partnershipDao.getPartnerships()
    }

    /**
     * Inserts a list of partnerships into the database by delegating to the corresponding DAO method.
     *
     * @param partnerships The list of partnership entities to be inserted.
     */
    override suspend fun insertAllPartnerships(partnerships: List<PartnershipRoomEntity>) {
        partnershipDao.insertAllPartnerships(partnerships)
    }

    /**
     * Clears all tables related to partnerships in the database by delegating to the corresponding DAO method.
     */
    override suspend fun clearAllTables() {
        partnershipDao.clearAllTables()
    }
}
