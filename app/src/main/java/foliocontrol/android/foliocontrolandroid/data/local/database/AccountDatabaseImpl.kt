package foliocontrol.android.foliocontrolandroid.data.local.database

import foliocontrol.android.foliocontrolandroid.data.local.dao.UserDao
import foliocontrol.android.foliocontrolandroid.data.local.schema.UserRoomEntity
import kotlinx.coroutines.flow.Flow

/**
 * Implementation of [AccountDatabase] that uses [UserDao] for database operations.
 *
 * @property userDao DAO for user-related database operations.
 */
class AccountDatabaseImpl(private val userDao: UserDao) : AccountDatabase {
    /**
     * Retrieves the user information as a Flow by delegating to [UserDao.getUser].
     *
     * @return A [Flow] emitting the user information.
     */
    override suspend fun getUser(): Flow<UserRoomEntity> {
        return userDao.getUser()
    }

    /**
     * Inserts a [UserRoomEntity] into the database by delegating to [UserDao.insert].
     *
     * @param user The user entity to be inserted.
     */
    override suspend fun insertUser(user: UserRoomEntity) {
        userDao.insert(user)
    }

    /**
     * Clears all tables in the database by delegating to [UserDao.clearAllTables].
     */
    override suspend fun clearAllTables() {
        userDao.clearAllTables()
    }
}
