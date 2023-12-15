package foliocontrol.android.foliocontrolandroid.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import foliocontrol.android.foliocontrolandroid.data.local.schema.UserRoomEntity

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user: UserRoomEntity)

    @Query("SELECT * FROM user_table")
    fun getUser(): UserRoomEntity
}
