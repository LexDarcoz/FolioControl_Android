package foliocontrol.android.foliocontrolandroid.data.local.schema

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import foliocontrol.android.foliocontrolandroid.domain.Partnership
/**
 * Represents a Room database entity for the "partnership_table" table.
 *
 * @property partnershipID The unique identifier for the partnership.
 * @property name The name of the partnership.
 * @property logoImg The logo image URL associated with the partnership.
 * @property countryCode The country code of the partnership.
 * @property vatNumber The VAT (Value Added Tax) number of the partnership.
 * @property street The street address of the partnership.
 * @property streetNumber The street number of the partnership address.
 * @property zipCode The ZIP code of the partnership address.
 * @property city The city of the partnership address.
 * @property country The country of the partnership address.
 */
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
    val country: String = "",
)
/**
 * Extension function to convert [PartnershipRoomEntity] to the corresponding domain model [Partnership].
 *
 * @return The [Partnership] domain model.
 */
fun PartnershipRoomEntity.asDomainModel() =
    Partnership(
        partnershipID = partnershipID,
        name = name,
        logoImg = logoImg,
        countryCode = countryCode,
        vatNumber = vatNumber,
        street = street,
        streetNumber = streetNumber,
        zipCode = zipCode,
        city = city,
        country = country,
    )
