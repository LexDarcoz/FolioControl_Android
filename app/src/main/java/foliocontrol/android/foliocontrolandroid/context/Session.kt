package foliocontrol.android.foliocontrolandroid.context

data class Session(
    val user: User,
    val token: String,
    val expiresAt: Long
)

data class User(
    var token: String = "",
    var email: String = "",
    var name: String = "",
    var firstName: String = "",
    var lastName: String = "",
    var street: String = "",
    var streetNumber: String = "",
    var zipCode: String = "",
    var city: String = "",
    var country: String = ""

)
