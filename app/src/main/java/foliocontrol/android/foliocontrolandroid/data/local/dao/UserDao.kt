package foliocontrol.android.foliocontrolandroid.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import foliocontrol.android.foliocontrolandroid.data.local.schema.UserRoomEntity
import kotlinx.coroutines.flow.Flow

/**
 * Data Access Object (DAO) interface for the User entity, providing methods to interact with the underlying database.
 */
@Dao
interface UserDao {
    /**
     * Inserts a single user into the database. If there is a conflict with an existing entry, it replaces the existing entry.
     *
     * @param user The [UserRoomEntity] object to be inserted.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user: UserRoomEntity)

    /**
     * Retrieves the user from the database and emits it as a Flow. This allows for reactive programming when the data changes.
     *
     * @return [Flow] emitting a single [UserRoomEntity] object.
     */
    @Query("SELECT * FROM user_table LIMIT 1")
    fun getUser(): Flow<UserRoomEntity>

    /**
     * Clears all entries in the user table.
     */
    @Query("DELETE FROM user_table")
    suspend fun clearAllTables()
}
