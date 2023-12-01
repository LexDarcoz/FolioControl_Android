package foliocontrol.android.foliocontrolandroid.data.local.database

import androidx.compose.runtime.collectAsState
import foliocontrol.android.foliocontrolandroid.data.local.dao.PropertyDao
import foliocontrol.android.foliocontrolandroid.domain.Partnership
import foliocontrol.android.foliocontrolandroid.domain.Property

class PropertyDatabaseImpl(private val propertyDao: PropertyDao) : PropertyDatabase {
    override suspend fun getPropertiesByActivePartnership(partnership: Partnership): List<Property> {
        return propertyDao.getAllProperties().collectAsState(initial = emptyList()).value
    }
}
