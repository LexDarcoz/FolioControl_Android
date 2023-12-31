package foliocontrol.android.foliocontrolandroid.ui.viewModels.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

/**
 * Composable function that displays a loading screen with a circular progress indicator.
 *
 * @see CircularProgressIndicator
 *
 * @sample foliocontrol.android.foliocontrolandroid.ui.viewModels.common.LoadingScreen
 */
@Composable
fun LoadingScreen() {
    Box(
        modifier =
            Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background.copy(alpha = 0.9f))
                .padding(16.dp),
        contentAlignment = Alignment.Center,
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier =
                Modifier
                    .background(MaterialTheme.colorScheme.background.copy(alpha = 0.9f))
                    .padding(16.dp)
                    .clip(RoundedCornerShape(8.dp)),
        ) {
            CircularProgressIndicator(
                modifier =
                    Modifier
                        .size(150.dp)
                        .padding(16.dp),
                color = MaterialTheme.colorScheme.primary,
            )
            Text(
                text = "Loading...",
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.Bold,
            )
        }
    }
}
