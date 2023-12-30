package foliocontrol.android.foliocontrolandroid.data.local.auth

import foliocontrol.android.foliocontrolandroid.data.local.getEncryptedPreference
import foliocontrol.android.foliocontrolandroid.data.local.removeEncryptedPreference
import foliocontrol.android.foliocontrolandroid.data.local.saveEncryptedPreference

interface TokenRepo {
    fun getToken(): String
    fun setToken(token: String)
    fun removeToken()
}


class TokenRepoImpl : TokenRepo {
    override fun getToken(): String {
        return getEncryptedPreference("token")
    }

    override fun setToken(token: String) {
        saveEncryptedPreference("token", token)
    }

    override fun removeToken() {
        removeEncryptedPreference("token")
    }

}