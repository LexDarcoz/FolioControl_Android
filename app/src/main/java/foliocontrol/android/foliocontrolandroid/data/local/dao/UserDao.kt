package foliocontrol.android.foliocontrolandroid.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import foliocontrol.android.foliocontrolandroid.data.local.schema.UserRoomEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user: UserRoomEntity)

    @Query("SELECT * FROM user_table LIMIT 1")
    fun getUser(): Flow<UserRoomEntity>


    @Query("DELETE FROM user_table")
    suspend fun clearAllTables()

}
