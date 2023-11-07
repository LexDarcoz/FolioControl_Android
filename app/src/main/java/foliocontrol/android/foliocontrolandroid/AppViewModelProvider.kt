package foliocontrol.android.foliocontrolandroid

import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import foliocontrol.android.foliocontrolandroid.domain.viewModels.AuthViewModel
import foliocontrol.android.foliocontrolandroid.domain.viewModels.PropertyViewModel

object AppViewModelProvider {
    val Factory = viewModelFactory {
        initializer {
            AuthViewModel()
        }
        initializer { PropertyViewModel() }
    }
}
