package foliocontrol.android.foliocontrolandroid

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import foliocontrol.android.foliocontrolandroid.ui.viewModels.AccountViewModel
import foliocontrol.android.foliocontrolandroid.ui.viewModels.AuthViewModel
import foliocontrol.android.foliocontrolandroid.ui.viewModels.PropertyViewModel

object AppViewModelProvider {
    val Factory = viewModelFactory {
        initializer {
            AuthViewModel(    propertyRepo = foliocontrolApplication().container.propertyDatabase,
                partnershipRepo = foliocontrolApplication().container.partnershipDatabase,accountRepo = foliocontrolApplication().container.accountDatabase)
        }
        initializer {
            PropertyViewModel(
                propertyRepo = foliocontrolApplication().container.propertyDatabase,
                partnershipRepo = foliocontrolApplication().container.partnershipDatabase,
                downloader = foliocontrolApplication().downloader
            )
        }
        initializer {
            AccountViewModel(
                accountRepo = foliocontrolApplication().container.accountDatabase
            )
        }
    }
}

fun CreationExtras.foliocontrolApplication(): FolioControlApp =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as FolioControlApp)
