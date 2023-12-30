package foliocontrol.android.foliocontrolandroid.domain

import androidx.compose.runtime.Immutable
import foliocontrol.android.foliocontrolandroid.data.local.schema.PartnershipRoomEntity
import foliocontrol.android.foliocontrolandroid.data.local.schema.PropertyRoomEntity
import foliocontrol.android.foliocontrolandroid.data.local.schema.UserRoomEntity
import kotlinx.serialization.Serializable

@Serializable
data class LoginCredentials(
    val email: String = "",
    val password: String = "",
)

@Serializable
@Immutable
data class User(
    val userID: Int = 0,
    val name: String = "",
    val firstName: String = "",
    val lastName: String = "",
    val street: String = "",
    val streetNumber: String = "",
    val zipCode: String = "",
    val city: String = "",
    val country: String = "",
    val email: String = "",
)

@Serializable
@Immutable
data class UserDto(
    val name: String = "",
    val firstName: String = "",
    val lastName: String = "",
    val street: String = "",
    val streetNumber: String = "",
    val zipCode: String = "",
    val city: String = "",
    val country: String = "",
    val email: String = "",
)

@Serializable
@Immutable
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
    val propertySize: String = "",
    val propertyPrice: String = "",
    val propertyDescription: String = "",
    val FK_partnershipID: Int = 0,
)

@Serializable
@Immutable
data class Premise(
    val premiseID: Int = 0,
    val premisesName: String = "",
    val bus: String = "",
    val rent: String = "",
    val img: String = "",
    val description: String = "",
    val address: String = "",
    val isActive: Int = 0,
    val isRented: Int = 0,
    val tenant: String = "",
    val FK_propertyID: Int = 0,
)

@Serializable
@Immutable
data class PropertyDocument(
    val propertyDocumentID: Int = 0,
    val name: String = "",
    val documentName: String = "",
    val documentType: String = "",
    val expiryDate: String = "",
    val propertyId: Int = 0,
)

fun Property.asPropertyRoomEntity() =
    PropertyRoomEntity(
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

fun User.asUserRoomEntity() =
    UserRoomEntity(
        userID = userID,
        name = name,
        firstName = firstName,
        lastName = lastName,
        street = street,
        streetNumber = streetNumber,
        zipCode = zipCode,
        city = city,
        country = country,
        email = email,
    )

@Serializable
@Immutable
data class Partnership(
    val partnershipID: Int = 0,
    val name: String = "",
    val logoImg: String = "",
    val countryCode: String = "",
    val vatNumber: String = "",
    val street: String = "",
    val streetNumber: String = "",
    val zipCode: String = "",
    val city: String = "",
    val country: String = "",
)

fun Partnership.asPartnershipRoomEntity() =
    PartnershipRoomEntity(
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

@Serializable
data class Token(
    val token: String = "",
    val validated: Boolean = false,
)
