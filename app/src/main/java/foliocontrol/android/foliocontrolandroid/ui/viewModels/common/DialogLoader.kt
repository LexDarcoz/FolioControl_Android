package foliocontrol.android.foliocontrolandroid.ui.viewModels.common

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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

/**
 * Composable function for displaying a loading dialog with a circular progress indicator and optional loading text.
 *
 * @param modifier The modifier for the [Box] containing the loading dialog.
 * @param progressBarColor The color of the circular progress indicator.
 * @param loadingText The text displayed below the progress indicator to indicate the loading state.
 */
@Composable
fun DialogLoader(
    modifier: Modifier = Modifier,
    progressBarColor: Color = MaterialTheme.colorScheme.primary,
    loadingText: String = "Loading...",
) {
    Box(
        modifier =
            Modifier
                .fillMaxSize()
                .then(modifier),
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier =
                Modifier
                    .padding(16.dp)
                    .clip(RoundedCornerShape(8.dp)),
        ) {
            CircularProgressIndicator(
                modifier =
                    Modifier
                        .size(150.dp)
                        .padding(16.dp),
                color = progressBarColor,
            )
            Text(
                text = loadingText,
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.Bold,
            )
        }
    }
}
