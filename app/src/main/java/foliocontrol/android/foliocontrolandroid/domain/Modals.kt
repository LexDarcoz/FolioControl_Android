package foliocontrol.android.foliocontrolandroid.domain

import androidx.compose.runtime.Immutable
import foliocontrol.android.foliocontrolandroid.data.local.schema.PartnershipRoomEntity
import foliocontrol.android.foliocontrolandroid.data.local.schema.PropertyRoomEntity
import foliocontrol.android.foliocontrolandroid.data.local.schema.UserRoomEntity
import kotlinx.serialization.Serializable

/**
 * Data class representing login credentials, including email and password.
 *
 * @property email The email associated with the login credentials.
 * @property password The password associated with the login credentials.
 */
@Serializable
data class LoginCredentials(
    val email: String = "",
    val password: String = "",
)

/**
 * Immutable data class representing user information.
 *
 * @property userID The unique identifier for the user.
 * @property name The full name of the user.
 * @property firstName The first name of the user.
 * @property lastName The last name of the user.
 * @property street The street address of the user.
 * @property streetNumber The street number of the user's address.
 * @property zipCode The ZIP code of the user's address.
 * @property city The city of the user's address.
 * @property country The country of the user's address.
 * @property email The email address of the user.
 */
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

/**
 * Immutable data class representing user information for data transfer.
 *
 * @property name The full name of the user.
 * @property firstName The first name of the user.
 * @property lastName The last name of the user.
 * @property street The street address of the user.
 * @property streetNumber The street number of the user's address.
 * @property zipCode The ZIP code of the user's address.
 * @property city The city of the user's address.
 * @property country The country of the user's address.
 * @property email The email address of the user.
 */
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

/**
 * Immutable data class representing property information.
 *
 * @property propertyID The unique identifier for the property.
 * @property propertyName The name of the property.
 * @property propertyType The type of the property.
 * @property propertyImg The image URL of the property.
 * @property street The street address of the property.
 * @property streetNumber The street number of the property's address.
 * @property city The city of the property's address.
 * @property zipCode The ZIP code of the property's address.
 * @property country The country of the property's address.
 * @property propertySize The size of the property.
 * @property propertyPrice The price of the property.
 * @property propertyDescription The description of the property.
 * @property FK_partnershipID The foreign key linking the property to a partnership.
 */
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

/**
 * Immutable data class representing premises information.
 *
 * @property premiseID The unique identifier for the premises.
 * @property premisesName The name of the premises.
 * @property bus The business conducted at the premises.
 * @property rent The rent associated with the premises.
 * @property img The image URL of the premises.
 * @property description The description of the premises.
 * @property address The address of the premises.
 * @property isActive The flag indicating whether the premises is active.
 * @property isRented The flag indicating whether the premises is rented.
 * @property tenant The tenant associated with the premises.
 * @property FK_propertyID The foreign key linking the premises to a property.
 */
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

/**
 * Immutable data class representing property document information.
 *
 * @property propertyDocumentID The unique identifier for the property document.
 * @property name The name associated with the property document.
 * @property documentName The document name.
 * @property documentType The type of the document.
 * @property expiryDate The expiry date of the document.
 * @property propertyId The foreign key linking the document to a property.
 */
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

/**
 * Extension function converting [Property] to [PropertyRoomEntity].
 *
 * @return [PropertyRoomEntity] representing the property.
 */
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

/**
 * Extension function converting [User] to [UserRoomEntity].
 *
 * @return [UserRoomEntity] representing the user.
 */
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

/**
 * Immutable data class representing partnership information.
 *
 * @property partnershipID The unique identifier for the partnership.
 * @property name The name of the partnership.
 * @property logoImg The image URL of the partnership logo.
 * @property countryCode The country code of the partnership.
 * @property vatNumber The VAT number of the partnership.
 * @property street The street address of the partnership.
 * @property streetNumber The street number of the partnership's address.
 * @property zipCode The ZIP code of the partnership's address.
 * @property city The city of the partnership's address.
 * @property country The country of the partnership's address.
 */
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

/**
 * Extension function converting [Partnership] to [PartnershipRoomEntity].
 *
 * @return [PartnershipRoomEntity] representing the partnership.
 */
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

/**
 * Data class representing an authentication token.
 *
 * @property token The authentication token.
 * @property validated A flag indicating whether the token has been validated.
 */
@Serializable
data class Token(
    val token: String = "",
    val validated: Boolean = false,
)
