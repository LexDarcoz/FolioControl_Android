package foliocontrol.android.foliocontrolandroid.ui.screens.portfolio

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun PropertyDocumentsScreen() {
    Column {
        Box(
            modifier = Modifier.fillMaxSize()

        ) {
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                Text(
                    text = "Property Documents screen",
                    style = MaterialTheme.typography.displayLarge
                )
            }
        }
    }
}
