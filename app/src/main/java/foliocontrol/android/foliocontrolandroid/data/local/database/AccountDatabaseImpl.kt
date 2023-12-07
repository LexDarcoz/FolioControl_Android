package foliocontrol.android.foliocontrolandroid.data.local.database

import foliocontrol.android.foliocontrolandroid.data.local.dao.UserDao
import foliocontrol.android.foliocontrolandroid.data.local.schema.PartnershipRoomEntity
import foliocontrol.android.foliocontrolandroid.data.local.schema.UserRoomEntity

class AccountDatabaseImpl(private val userDao: UserDao) : AccountDatabase {
    override suspend fun getAccountByToken(token: String): UserRoomEntity {
        return userDao.getUser()
    }

    override suspend fun getPartnershipsByLoggedInUser(token: String): List<PartnershipRoomEntity> {
        TODO("Not yet implemented")
    }

    override suspend fun insertUser(user: UserRoomEntity) {
        userDao.insert(user)
    }
}
