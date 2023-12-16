package foliocontrol.android.foliocontrolandroid.domain

import foliocontrol.android.foliocontrolandroid.data.local.schema.PartnershipRoomEntity
import foliocontrol.android.foliocontrolandroid.data.local.schema.PropertyRoomEntity
import foliocontrol.android.foliocontrolandroid.data.local.schema.UserRoomEntity
import kotlinx.serialization.Serializable

data class LoginCredentials(
    var email: String = "",
    var password: String = ""

)

data class User(
    var name: String = "",
    var firstName: String = "",
    var lastName: String = "",
    var street: String = "",
    var streetNumber: String = "",
    var zipCode: String = "",
    var city: String = "",
    var country: String = "",
    var email: String = ""
)

@Serializable
data class Property(
    val propertyID: Int = 0,
    val propertyName: String = "",
    val propertyType: String = "",
    val propertyImg: String = "",
    val street: String = "",
    val streetNumber: String = "",
    val city: String = "",
    val zipCode: String = "",
    val country: String = "",
    val propertyDescription: String = "",
    val FK_partnershipID: Int = 0
)

fun Property.asPropertyRoomEntity() = PropertyRoomEntity(
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

fun User.asUserRoomEntity() = UserRoomEntity(
    name = name,
    firstName = firstName,
    lastName = lastName,
    street = street,
    streetNumber = streetNumber,
    zipCode = zipCode,
    city = city,
    country = country,
    email = email
)

data class Partnership(
    var partnershipID: Int = 0,
    var name: String = "",
    var logoImg: String = "",
    var countryCode: String = "",
    var vatNumber: String = "",
    var street: String = "",
    var streetNumber: String = "",
    var zipCode: String = "",
    var city: String = "",
    var country: String = ""

)
fun Partnership.asPartnershipRoomEntity() = PartnershipRoomEntity(
    partnershipID = partnershipID,
    name = name,
    logoImg = logoImg,
    countryCode = countryCode,
    vatNumber = vatNumber,
    street = street,
    streetNumber = streetNumber,
    zipCode = zipCode,
    city = city,
    country = country
)


data class Token(
    var token: String = ""
)
