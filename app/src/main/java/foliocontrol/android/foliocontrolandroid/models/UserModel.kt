package foliocontrol.android.foliocontrolandroid.models

data class UserModel(

    var id: String,
    var token: String,
    var email: String,
    var password: String,
    var name: String,
    var firstName: String,
    var lastName: String,
    var street: String,
    var streetNumber: String,
    var zipCode: String,
    var city: String,
    var country: String

)
