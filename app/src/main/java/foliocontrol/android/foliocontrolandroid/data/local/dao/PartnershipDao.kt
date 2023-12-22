package foliocontrol.android.foliocontrolandroid.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import foliocontrol.android.foliocontrolandroid.data.local.schema.PartnershipRoomEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PartnershipDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllPartnerships(partnership: List<PartnershipRoomEntity>)

    @Query("SELECT * FROM partnership_table")
    fun getPartnerships(): Flow<List<PartnershipRoomEntity>>

    @Query("DELETE FROM partnership_table")
    suspend fun clearAllTables()
}
