package foliocontrol.android.foliocontrolandroid.context

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
    var name: String = "",
    var value: String = "",
)

data class Token(
    var token: String = "",
)


