package foliocontrol.android.foliocontrolandroid.data.local

import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import foliocontrol.android.foliocontrolandroid.MainApplication

val masterKeyAlias = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC)

fun getEncryptedPreference(key: String): String {
    return getSharedPreferences().getString(key, "") ?: ""
}

fun saveEncryptedPreference(key: String, preference: String) {
    getSharedPreferences().edit().putString(key, preference).apply()
}

fun removeEncryptedPreference(key: String) {
    getSharedPreferences().edit().remove(key).apply()
}

private fun getSharedPreferences(): SharedPreferences {
    return EncryptedSharedPreferences.create(
        "preferences",
        masterKeyAlias,
        MainApplication.appContext,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )
}
