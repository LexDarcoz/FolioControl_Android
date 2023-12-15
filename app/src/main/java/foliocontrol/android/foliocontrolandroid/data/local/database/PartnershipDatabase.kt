package foliocontrol.android.foliocontrolandroid.data.local.database

import foliocontrol.android.foliocontrolandroid.data.local.schema.PartnershipRoomEntity

interface PartnershipDatabase {
    suspend fun getPartnershipByPartnershipNumber(partnershipNumber: String): PartnershipRoomEntity
    suspend fun getPartnershipsByLoggedInUser(token: String): List<PartnershipRoomEntity>



}