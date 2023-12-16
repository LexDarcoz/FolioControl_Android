package foliocontrol.android.foliocontrolandroid.data.local.database

import foliocontrol.android.foliocontrolandroid.data.local.schema.PartnershipRoomEntity
import foliocontrol.android.foliocontrolandroid.data.local.schema.UserRoomEntity
import kotlinx.coroutines.flow.Flow

interface AccountDatabase {
    suspend fun getUser(): Flow<UserRoomEntity>
    suspend fun getPartnershipsByLoggedInUser(token: String): List<PartnershipRoomEntity>

    suspend fun insertUser(user: UserRoomEntity)
}
