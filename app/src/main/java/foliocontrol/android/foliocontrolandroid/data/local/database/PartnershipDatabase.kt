package foliocontrol.android.foliocontrolandroid.data.local.database

import foliocontrol.android.foliocontrolandroid.data.local.schema.PartnershipRoomEntity
import kotlinx.coroutines.flow.Flow

interface PartnershipDatabase {
    fun getPartnerships(): Flow<List<PartnershipRoomEntity>>
    suspend fun insertAllPartnerships(partnerships: List<PartnershipRoomEntity>)
}
