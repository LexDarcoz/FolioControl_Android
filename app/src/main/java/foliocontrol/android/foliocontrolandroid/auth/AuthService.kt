package foliocontrol.android.foliocontrolandroid.auth

import android.content.Context
import foliocontrol.android.foliocontrolandroid.context.LoginState
import foliocontrol.android.foliocontrolandroid.context.SignUpState

interface AuthService {

    fun configureAuth(context: Context)

    fun signUp(signUpState: SignUpState, onComplete: () -> Unit)
    fun login(loginState: LoginState, onComplete: () -> Unit)
    fun logOut(onComplete: () -> Unit)
}
