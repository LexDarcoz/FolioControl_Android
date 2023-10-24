package foliocontrol.android.foliocontrolandroid.context

data class SignUpState(
    var username: String = "",
    var password: String = "",
    var email: String = ""

)

data class LoginState(
    var email: String = "",
    var password: String = ""

)

data class User(
    var name: String = "",
    var firstName: String = "",
    var lastName: String = "",

    var email: String = "",

    var token: String = "",

    )
