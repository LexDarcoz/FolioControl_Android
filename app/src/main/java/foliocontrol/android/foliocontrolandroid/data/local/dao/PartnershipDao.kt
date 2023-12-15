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
    suspend fun insert(partnership: PartnershipRoomEntity)

    @Query("SELECT * FROM partnership_table WHERE partnership_id LIKE :partnershipId")
    fun getPartnershipByPartnershipID(partnershipId: String): Flow<PartnershipRoomEntity>
}
