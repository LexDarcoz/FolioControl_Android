package foliocontrol.android.foliocontrol_android

import foliocontrol.android.foliocontrolandroid.data.local.database.PartnershipDatabase
import foliocontrol.android.foliocontrolandroid.data.local.schema.PartnershipRoomEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakePartnershipDatabase : PartnershipDatabase {

    private val partnershipsList = mutableListOf<PartnershipRoomEntity>()

    override fun getPartnerships(): Flow<List<PartnershipRoomEntity>> {
        return flow {
            emit(partnershipsList)
        }
    }

    override suspend fun insertAllPartnerships(partnerships: List<PartnershipRoomEntity>) {
        partnershipsList.addAll(partnerships)
    }

    override suspend fun clearAllTables() {
        partnershipsList.clear()
    }
}