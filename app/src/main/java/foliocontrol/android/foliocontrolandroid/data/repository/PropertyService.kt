package foliocontrol.android.foliocontrolandroid.data.repository

import foliocontrol.android.foliocontrolandroid.data.Property

interface PropertyService {

    suspend fun getProperties(): List<Property>
}
