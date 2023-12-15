package foliocontrol.android.foliocontrolandroid.data.local.database

import foliocontrol.android.foliocontrolandroid.data.local.schema.PartnershipRoomEntity
import foliocontrol.android.foliocontrolandroid.data.local.schema.UserRoomEntity

interface AccountDatabase {
    suspend fun getAccountByToken(token: String): UserRoomEntity
    suspend fun getPartnershipsByLoggedInUser(token: String): List<PartnershipRoomEntity>

    suspend fun insertUser(user: UserRoomEntity)
}
