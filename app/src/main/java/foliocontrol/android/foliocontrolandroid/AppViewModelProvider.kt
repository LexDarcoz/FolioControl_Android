package foliocontrol.android.foliocontrolandroid

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import foliocontrol.android.foliocontrolandroid.ui.viewModels.AccountViewModel
import foliocontrol.android.foliocontrolandroid.ui.viewModels.AuthViewModel
import foliocontrol.android.foliocontrolandroid.ui.viewModels.PropertyViewModel

/**
 * Singleton object providing a [ViewModelProvider.Factory] for creating instances of ViewModels.
 * The provided factory initializes [AuthViewModel], [PropertyViewModel], and [AccountViewModel] with
 * appropriate repositories.
 */
object AppViewModelProvider {
    /** Factory for creating instances of ViewModels with appropriate dependencies. */
    val Factory =
        viewModelFactory {
            // Initialize AuthViewModel with property, partnership, and account repositories.
            initializer {
                AuthViewModel(
                    propertyRepo = foliocontrolApplication().container.propertyDatabase,
                    partnershipRepo = foliocontrolApplication().container.partnershipDatabase,
                    accountRepo = foliocontrolApplication().container.accountDatabase,
                )
            }

            // Initialize PropertyViewModel with property, partnership, and downloader dependencies.
            initializer {
                PropertyViewModel(
                    propertyRepo = foliocontrolApplication().container.propertyDatabase,
                    partnershipRepo = foliocontrolApplication().container.partnershipDatabase,
                    downloader = foliocontrolApplication().downloader,
                )
            }

            // Initialize AccountViewModel with the account repository.
            initializer {
                AccountViewModel(
                    accountRepo = foliocontrolApplication().container.accountDatabase,
                )
            }
        }
}

/**
 * Extension function for [CreationExtras] to retrieve the [FolioControlApp] instance from
 * the provided [ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY].
 *
 * @return The [FolioControlApp] instance.
 */
fun CreationExtras.foliocontrolApplication(): FolioControlApp =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as FolioControlApp)
