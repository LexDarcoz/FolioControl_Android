package foliocontrol.android.foliocontrolandroid.auth

import android.content.Context
import foliocontrol.android.foliocontrolandroid.context.LoginState


interface AuthService {

    fun configureAuth(context: Context)


    suspend fun login(
        loginState: LoginState,
        updateTokenState: (String) -> Unit,
    ): Boolean

    fun logOut(onComplete: () -> Unit)




}
