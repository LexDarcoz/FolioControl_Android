package foliocontrol.android.foliocontrolandroid.domain.dataModels

data class LoginState(
    var email: String = "",
    var password: String = ""

)

data class User(
    var name: String = "",
    var firstName: String = "",
    var lastName: String = "",
    var email: String = ""
)

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

data class Token(
    var token: String = ""
)
