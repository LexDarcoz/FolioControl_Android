package foliocontrol.android.foliocontrolandroid.models

data class ProfileModel(
    var name: String,
    var firstName: String,
    var lastName: String,
    var street: String,
    var streetNumber: String,
    var zipCode: String,
    var city: String,
    var email: String,
    )

data class UserModel(
    var profile: ProfileModel,
    var token: String
)