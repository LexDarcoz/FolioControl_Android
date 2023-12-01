package foliocontrol.android.foliocontrolandroid.data.local.schema

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import foliocontrol.android.foliocontrolandroid.domain.Property

@Entity(tableName = "property_table")
data class PropertyRoomEntity(
    @ColumnInfo(name = "property_id")
    @PrimaryKey
    val propertyID: Int = 0,
    @ColumnInfo(name = "property_name")
    val propertyName: String = "",
    @ColumnInfo(name = "property_type")
    val propertyType: String = "",
    @ColumnInfo(name = "property_img")
    val propertyImg: String = "",
    @ColumnInfo(name = "street")
    val street: String = "",
    @ColumnInfo(name = "street_number")
    val streetNumber: String = "",
    @ColumnInfo(name = "city")
    val city: String = "",
    @ColumnInfo(name = "zip_code")
    val zipCode: String = "",
    @ColumnInfo(name = "country")
    val country: String = "",
    @ColumnInfo(name = "property_description")
    val propertyDescription: String = "",
    @ColumnInfo(name = "FK_partnership_id")
    val FK_partnershipID: Int = 0
)

fun PropertyRoomEntity.asDomainModel() =
    Property(
        propertyID = propertyID,
        propertyName = propertyName,
        propertyType = propertyType,
        propertyImg = propertyImg,
        street = street,
        streetNumber = streetNumber,
        city = city,
        zipCode = zipCode,
        country = country,
        propertyDescription = propertyDescription,
        FK_partnershipID = FK_partnershipID
    )
