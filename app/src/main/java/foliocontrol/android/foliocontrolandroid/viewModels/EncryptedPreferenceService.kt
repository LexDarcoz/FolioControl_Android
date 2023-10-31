package foliocontrol.android.foliocontrolandroid.viewModels

import android.content.Context
import android.util.Log
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys


fun getEncryptedPreference(pref : String, context: Context): String? {

    val masterKeyAlias = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC)

    var sharedPreferences = EncryptedSharedPreferences.create(
        "preferences",
        MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC),
        context,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )

    return sharedPreferences.getString("token", "")
}

fun setEncryptedPreference(token: String, context: Context) {
// creating a master key for encryption of shared preferences.
    val masterKeyAlias = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC)

    // Initialize/open an instance of EncryptedSharedPreferences on below line.
    val sharedPreferences = EncryptedSharedPreferences.create(
        // passing a file name to share a preferences
        "preferences",
        masterKeyAlias,
        context,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )

    sharedPreferences.edit().putString("token", token).apply()

    // on below line creating a variable
    // to get the data from shared prefs.
    val token = sharedPreferences.getString("token", "")

    Log.d("token in preferences", token.toString())
//    val ageStr = sharedPreferences.getString("age", "")

}