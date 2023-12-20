package foliocontrol.android.foliocontrolandroid.data.local.database

import foliocontrol.android.foliocontrolandroid.data.local.dao.PartnershipDao
import foliocontrol.android.foliocontrolandroid.data.local.schema.PartnershipRoomEntity
import kotlinx.coroutines.flow.Flow

class PartnershipDatabaseImpl(private val partnershipDao: PartnershipDao) : PartnershipDatabase {
    override fun getPartnerships(): Flow<List<PartnershipRoomEntity>> {
        return partnershipDao.getPartnerships()
    }

    override suspend fun insertAllPartnerships(partnerships: List<PartnershipRoomEntity>) {
        partnershipDao.insertAllPartnerships(partnerships)
    }
}
