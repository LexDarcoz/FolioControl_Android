package foliocontrol.android.foliocontrolandroid.data.local.database

import foliocontrol.android.foliocontrolandroid.data.local.schema.UserRoomEntity
import kotlinx.coroutines.flow.Flow

/**
 * Interface defining database operations related to the user account.
 */
interface AccountDatabase {
    /**
     * Retrieves the user information as a Flow.
     *
     * @return A [Flow] emitting the user information.
     */
    suspend fun getUser(): Flow<UserRoomEntity>

    /**
     * Inserts a [UserRoomEntity] into the database.
     *
     * @param user The user entity to be inserted.
     */
    suspend fun insertUser(user: UserRoomEntity)

    /**
     * Clears all tables in the database.
     */
    suspend fun clearAllTables()
}
