package foliocontrol.android.foliocontrolandroid.data.local.schema

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import foliocontrol.android.foliocontrolandroid.domain.User

/**
 * Represents a Room database entity for the "user_table" table.
 *
 * @property userID The unique identifier for the user.
 * @property name The full name of the user.
 * @property firstName The first name of the user.
 * @property lastName The last name of the user.
 * @property street The street address of the user.
 * @property streetNumber The street number of the user's address.
 * @property city The city of the user's address.
 * @property zipCode The ZIP code of the user's address.
 * @property country The country of the user's address.
 * @property email The email address of the user.
 */
@Entity(tableName = "user_table")
data class UserRoomEntity(
    @ColumnInfo(name = "userID") @PrimaryKey val userID: Int,
    @ColumnInfo(name = "name") val name: String = "",
    @ColumnInfo(name = "firstname") val firstName: String = "",
    @ColumnInfo(name = "lastname") val lastName: String = "",
    @ColumnInfo(name = "street") val street: String = "",
    @ColumnInfo(name = "street_number") val streetNumber: String = "",
    @ColumnInfo(name = "city") val city: String = "",
    @ColumnInfo(name = "zip_code") val zipCode: String = "",
    @ColumnInfo(name = "country") val country: String = "",
    @ColumnInfo(name = "email") val email: String = "",
)

/**
 * Extension function to convert [UserRoomEntity] to the corresponding domain model [User].
 *
 * @return The [User] domain model.
 */
fun UserRoomEntity.asDomainModel() = User(
    userID = userID,
    name = name,
    firstName = firstName,
    lastName = lastName,
    street = street,
    streetNumber = streetNumber,
    city = city,
    zipCode = zipCode,
    country = country,
    email = email,
)
