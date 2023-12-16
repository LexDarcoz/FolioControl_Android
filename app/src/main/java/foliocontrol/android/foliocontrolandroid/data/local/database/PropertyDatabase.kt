package foliocontrol.android.foliocontrolandroid.data.local.database

import foliocontrol.android.foliocontrolandroid.data.local.schema.PartnershipRoomEntity
import foliocontrol.android.foliocontrolandroid.data.local.schema.PropertyRoomEntity
import foliocontrol.android.foliocontrolandroid.domain.Partnership
import kotlinx.coroutines.flow.Flow

interface PropertyDatabase {
    fun getPropertiesByActivePartnership(partnership: Partnership): Flow<List<PropertyRoomEntity>>

    fun getAllProperties(): Flow<List<PropertyRoomEntity>>


    suspend fun insertAll(properties: List<PropertyRoomEntity>)
    suspend fun dropTable(partnershipID : Int)

}
