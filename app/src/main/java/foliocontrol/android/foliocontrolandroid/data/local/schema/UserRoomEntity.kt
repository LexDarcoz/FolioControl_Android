package foliocontrol.android.foliocontrolandroid.data.local.schema

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import foliocontrol.android.foliocontrolandroid.domain.User

@Entity(tableName = "user_table")
data class UserRoomEntity(
    @ColumnInfo(name = "userID")
    @PrimaryKey
    val userID: Int = 0,
    @ColumnInfo(name = "name")
    val name: String = "",
    @ColumnInfo(name = "firstname")
    val firstName: String = "",
    @ColumnInfo(name = "lastname")
    val lastName: String = "",
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
    @ColumnInfo(name = "email")
    val email: String = ""
)

fun UserRoomEntity.asDomainModel() =
    User(
        name = name,
        firstName = firstName,
        lastName = lastName,
        street = street,
        streetNumber = streetNumber,
        city = city,
        zipCode = zipCode,
        country = country,
        email = email
    )
