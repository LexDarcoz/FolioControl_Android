package foliocontrol.android.foliocontrolandroid.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import foliocontrol.android.foliocontrolandroid.data.local.schema.PropertyRoomEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PropertyDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(property: PropertyRoomEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(properties: List<PropertyRoomEntity>)

    @Query("SELECT * FROM property_table")
    fun getAllProperties(): Flow<List<PropertyRoomEntity>>
}
