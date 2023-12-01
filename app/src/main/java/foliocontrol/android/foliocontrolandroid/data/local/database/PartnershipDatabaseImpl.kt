package foliocontrol.android.foliocontrolandroid.data.local.database

import foliocontrol.android.foliocontrolandroid.data.local.dao.PartnershipDao
import foliocontrol.android.foliocontrolandroid.data.local.schema.PartnershipRoomEntity

class PartnershipDatabaseImpl(private val partnershipDao: PartnershipDao) : PartnershipDatabase {
    override suspend fun getPartnershipByPartnershipNumber(partnershipNumber: String): PartnershipRoomEntity {
        TODO("Not yet implemented")
    }

    override suspend fun getPartnershipsByLoggedInUser(token: String): List<PartnershipRoomEntity> {
        TODO("Not yet implemented")
    }
}
