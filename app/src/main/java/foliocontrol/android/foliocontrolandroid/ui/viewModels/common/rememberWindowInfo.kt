package foliocontrol.android.foliocontrolandroid.ui.viewModels.common

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * Composable function to remember window information based on the current configuration.
 * The window information includes screen width and height, as well as window types for both dimensions.
 *
 * @return WindowInfo containing information about the screen width, screen height, and window types.
 */
@Composable
fun rememberWindowInfo(): WindowInfo {
    val configuration = LocalConfiguration.current
    return WindowInfo(
        screenWidthInfo = when {
            configuration.screenWidthDp < 600 -> WindowInfo.WindowType.Compact
            configuration.screenWidthDp < 840 -> WindowInfo.WindowType.Medium
            else -> WindowInfo.WindowType.Expanded
        },
        screenHeightInfo = when {
            configuration.screenHeightDp < 600 -> WindowInfo.WindowType.Compact
            configuration.screenHeightDp < 840 -> WindowInfo.WindowType.Medium
            else -> WindowInfo.WindowType.Expanded
        },
        screenWidth = configuration.screenWidthDp.dp,
        screenHeight = configuration.screenHeightDp.dp,
    )
}

/**
 * Data class representing window information, including screen width and height,
 * as well as window types for both dimensions.
 *
 * @property screenWidthInfo WindowType representing the type of the screen width.
 * @property screenHeightInfo WindowType representing the type of the screen height.
 * @property screenWidth The screen width in Density-independent Pixels (Dp).
 * @property screenHeight The screen height in Density-independent Pixels (Dp).
 */
data class WindowInfo(
    val screenWidthInfo: WindowType,
    val screenHeightInfo: WindowType,
    val screenWidth: Dp,
    val screenHeight: Dp,
) {
    /**
     * Sealed class representing different window types.
     */
    sealed class WindowType {
        /**
         * Represents a compact window type.
         */
        object Compact : WindowType()

        /**
         * Represents a medium-sized window type.
         */
        object Medium : WindowType()

        /**
         * Represents an expanded window type.
         */
        object Expanded : WindowType()
    }
}
