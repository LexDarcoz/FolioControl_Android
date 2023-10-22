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
