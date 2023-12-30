package foliocontrol.android.foliocontrol_android

import foliocontrol.android.foliocontrolandroid.data.local.database.PropertyDatabase
import foliocontrol.android.foliocontrolandroid.data.local.schema.PropertyRoomEntity
import foliocontrol.android.foliocontrolandroid.domain.Partnership
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class FakePropertyDatabase : PropertyDatabase {
    val properties = mutableListOf<PropertyRoomEntity>()

    override fun getPropertiesByActivePartnership(partnership: Partnership): Flow<List<PropertyRoomEntity>> {
        TODO("Not yet implemented")
    }

    override fun getAllProperties(): Flow<List<PropertyRoomEntity>> {
        return flowOf(listOf(properties).first())
    }

    override suspend fun insertAll(properties: List<PropertyRoomEntity>) {

        properties.forEach {
            this.properties.add(it)
        }
    }

    override suspend fun dropTable(partnershipID: Int) {
        // drop tables...
        properties.clear()
    }

    override suspend fun clearAllTables() {
        properties.clear()
    }
}