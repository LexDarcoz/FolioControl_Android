package foliocontrol.android.foliocontrolandroid.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

/**
 * Composable function representing the offline screen.
 * It displays a message indicating that the user is offline and suggests checking the internet connection.
 */
@Composable
fun OfflineScreen() {
    // Customize this composable to show the offline screen
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = "You are offline. Please check your internet connection.",
            color = MaterialTheme.colorScheme.onSurface,
        )
    }
}
