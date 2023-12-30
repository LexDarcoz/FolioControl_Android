package foliocontrol.android.foliocontrol_android

import foliocontrol.android.foliocontrolandroid.data.local.auth.TokenRepo

class FakeTokenRepo : TokenRepo {
    override fun getToken(): String {
        return "fakeToken"
    }

    override fun setToken(token: String) {
        // do nothing
    }

    override fun removeToken() {
        // do nothing
    }


}