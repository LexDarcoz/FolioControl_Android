package foliocontrol.android.foliocontrolandroid.service

import foliocontrol.android.foliocontrolandroid.data.Property

interface PropertyService {

    suspend fun getProperties(): List<Property>
}
