package foliocontrol.android.foliocontrolandroid.screens

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun HomeScreen() {

    Text(
        text = "Home",
        color = MaterialTheme.colorScheme.primary,
        style = MaterialTheme.typography.bodyLarge
    )
}
