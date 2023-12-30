package foliocontrol.android.foliocontrolandroid.ui.viewModels.common

/**
 * A sealed interface representing different states for the user interface.
 */
sealed interface UiState {
    /**
     * Represents a success state with a specific message.
     *
     * @property message The success message.
     */
    data class Success(val message: String) : UiState

    /**
     * Represents a logged-out state with a specific message.
     *
     * @property message The message indicating the user is logged out.
     */
    data class LoggedOut(val message: String) : UiState

    /**
     * Represents an error state with a specific error message.
     *
     * @property message The error message.
     */
    data class Error(val message: String) : UiState

    /**
     * Represents an offline state with a specific message.
     *
     * @property message The message indicating the application is offline.
     */
    data class Offline(val message: String) : UiState

    /**
     * Represents a loading state indicating that an operation is in progress.
     */
    data object Loading : UiState
}
