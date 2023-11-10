package foliocontrol.android.foliocontrolandroid

import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import foliocontrol.android.foliocontrolandroid.ui.viewModels.AuthViewModel
import foliocontrol.android.foliocontrolandroid.ui.viewModels.PropertyViewModel

object AppViewModelProvider {
    val Factory = viewModelFactory {
        initializer {
            AuthViewModel()
        }
        initializer { PropertyViewModel() }
    }
}
