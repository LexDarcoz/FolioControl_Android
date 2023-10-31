package foliocontrol.android.foliocontrolandroid.data

data class LoginState(
    var email: String = "",
    var password: String = ""

)

data class User(
    var name: String = "",
    var firstName: String = "",
    var lastName: String = "",
    var email: String = "",
)

data class Property(
    var propertyID : Int = 0,
    var propertyName: String = "",
    var propertyType: String = "",
    var propertyImg: String = "",
    var street : String = "",
    var streetNumber : String = "",
    var city : String = "",
    var zipCode : String = "",
    var country : String = "",
    var propertyDescription : String = "",
    var FK_partnershipID : Int = 0,

)

data class Token(
    var token: String = "",
)


