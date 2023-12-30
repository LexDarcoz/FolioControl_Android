package foliocontrol.android.foliocontrol_android

import foliocontrol.android.foliocontrolandroid.data.local.database.AccountDatabase
import foliocontrol.android.foliocontrolandroid.data.local.schema.UserRoomEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeAccountDatabase : AccountDatabase {
    var user: UserRoomEntity? = null

    override suspend fun getUser(): Flow<UserRoomEntity> {
        return user?.let { flow{ emit(it) } } ?: throw Exception("User not found")

    }

    override suspend fun insertUser(user: UserRoomEntity) {
        this.user = user
    }

    override suspend fun clearAllTables() {
        user = null
    }
}