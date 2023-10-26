package foliocontrol.android.foliocontrolandroid

import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import foliocontrol.android.foliocontrolandroid.context.AuthViewModel

object AppViewModelProvider {
    val Factory = viewModelFactory {
        initializer {
            AuthViewModel()
        }
    }
}
