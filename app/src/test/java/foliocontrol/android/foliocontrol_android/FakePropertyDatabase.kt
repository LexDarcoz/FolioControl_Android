package foliocontrol.android.foliocontrol_android

import foliocontrol.android.foliocontrolandroid.data.local.database.PropertyDatabase
import foliocontrol.android.foliocontrolandroid.data.local.schema.PropertyRoomEntity
import foliocontrol.android.foliocontrolandroid.domain.Partnership
import kotlinx.coroutines.flow.Flow

class FakePropertyDatabase : PropertyDatabase {
    override fun getPropertiesByActivePartnership(partnership: Partnership): Flow<List<PropertyRoomEntity>> {
        TODO("Not yet implemented")
    }

    override fun getAllProperties(): Flow<List<PropertyRoomEntity>> {
        TODO("Not yet implemented")
    }

    override suspend fun insertAll(properties: List<PropertyRoomEntity>) {
        TODO("Not yet implemented")
    }

    override suspend fun dropTable(partnershipID: Int) {
        TODO("Not yet implemented")
    }

    override suspend fun clearAllTables() {
        TODO("Not yet implemented")
    }
}