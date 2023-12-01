package foliocontrol.android.foliocontrolandroid.data.local.schema

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "partnership_table")
data class PartnershipRoomEntity(
    @ColumnInfo(name = "partnership_id")
    @PrimaryKey(autoGenerate = true)
    val partnershipID: Int = 0,
    @ColumnInfo(name = "name")
    val name: String = "",
    @ColumnInfo(name = "logo_img")
    val logoImg: String = "",
    @ColumnInfo(name = "country_code")
    val countryCode: String = "",
    @ColumnInfo(name = "vat_number")
    val vatNumber: String = "",
    @ColumnInfo(name = "street")
    val street: String = "",
    @ColumnInfo(name = "street_number")
    val streetNumber: String = "",
    @ColumnInfo(name = "zip_code")
    val zipCode: String = "",
    @ColumnInfo(name = "city")
    val city: String = "",
    @ColumnInfo(name = "country")
    val country: String = ""
)
