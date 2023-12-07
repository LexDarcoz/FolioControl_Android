package foliocontrol.android.foliocontrolandroid.ui.viewModels.common

sealed interface UiState {

    data class Success(val message: String) : UiState
    data class LoggedOut(val message: String) : UiState
    data class Error(val message: String) : UiState
    data class Offline(val message: String) : UiState
    data object Loading : UiState
}
