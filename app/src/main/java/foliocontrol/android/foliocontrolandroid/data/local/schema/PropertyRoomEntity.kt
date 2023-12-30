package foliocontrol.android.foliocontrolandroid.data.local.schema

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import foliocontrol.android.foliocontrolandroid.domain.Property

/**
 * Represents a Room database entity for the "property_table" table.
 *
 * @property propertyID The unique identifier for the property.
 * @property propertyName The name of the property.
 * @property propertyType The type or category of the property.
 * @property propertyImg The property image URL.
 * @property street The street address of the property.
 * @property streetNumber The street number of the property address.
 * @property city The city of the property address.
 * @property zipCode The ZIP code of the property address.
 * @property country The country of the property address.
 * @property propertyDescription The description of the property.
 * @property FK_partnershipID The foreign key referencing the associated partnership.
 */
@Entity(tableName = "property_table")
data class PropertyRoomEntity(
    @ColumnInfo(name = "property_id") @PrimaryKey val propertyID: Int = 0,
    @ColumnInfo(name = "property_name") val propertyName: String = "",
    @ColumnInfo(name = "property_type") val propertyType: String = "",
    @ColumnInfo(name = "property_img") val propertyImg: String = "",
    @ColumnInfo(name = "street") val street: String = "",
    @ColumnInfo(name = "street_number") val streetNumber: String = "",
    @ColumnInfo(name = "city") val city: String = "",
    @ColumnInfo(name = "zip_code") val zipCode: String = "",
    @ColumnInfo(name = "country") val country: String = "",
    @ColumnInfo(name = "property_description") val propertyDescription: String = "",
    @ColumnInfo(name = "FK_partnership_id") val FK_partnershipID: Int = 0,
)

/**
 * Extension function to convert [PropertyRoomEntity] to the corresponding domain model [Property].
 *
 * @return The [Property] domain model.
 */
fun PropertyRoomEntity.asDomainModel() = Property(
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
    FK_partnershipID = FK_partnershipID,
)
