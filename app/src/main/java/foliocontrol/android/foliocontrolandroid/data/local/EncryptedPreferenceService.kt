package foliocontrol.android.foliocontrolandroid.data.local

import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import foliocontrol.android.foliocontrolandroid.MainActivity

/**
 * The master key alias used for creating or retrieving the master key.
 */
val masterKeyAlias = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC)

/**
 * Retrieves the encrypted preference for the given key.
 *
 * @param key The key for the preference.
 * @return The encrypted preference value as a String.
 */
fun getEncryptedPreference(key: String): String {
    return getSharedPreferences().getString(key, "") ?: ""
}

/**
 * Saves the encrypted preference for the given key.
 *
 * @param key The key for the preference.
 * @param preference The value of the preference to be saved.
 */
fun saveEncryptedPreference(
    key: String,
    preference: String,
) {
    getSharedPreferences().edit().putString(key, preference).apply()
}

/**
 * Removes the encrypted preference for the given key.
 *
 * @param key The key for the preference to be removed.
 */
fun removeEncryptedPreference(key: String) {
    getSharedPreferences().edit().remove(key).apply()
}

/**
 * Retrieves the EncryptedSharedPreferences instance.
 *
 * @return The EncryptedSharedPreferences instance.
 */
private fun getSharedPreferences(): SharedPreferences {
    return EncryptedSharedPreferences.create(
        "preferences",
        masterKeyAlias,
        MainActivity.appContext,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM,
    )
}
