package foliocontrol.android.foliocontrolandroid.ui.viewModels.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import foliocontrol.android.foliocontrol_android.R

/**
 * Composable function for displaying an empty list screen with a specified text message.
 *
 * @param text The text message to be displayed on the empty list screen. Default is "No data available."
 */
@Composable
fun EmptyListScreen(text: String = "No data available.") {
    Box(
        modifier =
            Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.8f)
                .padding(top = 32.dp),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = text,
            color = MaterialTheme.colorScheme.onSurface,
        )
    }
}

/**
 * Composable function for displaying an error screen with an error message and a retry button.
 *
 * @param errorMessage The error message to be displayed on the error screen.
 * @param onRetry The callback function to be invoked when the user clicks the retry button.
 */
@Composable
fun ErrorScreen(
    errorMessage: String,
    onRetry: () -> Unit,
) {
    Column(
        modifier =
            Modifier
                .fillMaxSize()
                .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
            painter = rememberAsyncImagePainter(model = R.drawable.errorimg),
            contentDescription = null,
            Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.4f),
        )

        Text(
            text = errorMessage,
            fontSize = 20.sp,
            color = MaterialTheme.colorScheme.error,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(bottom = 16.dp),
            fontWeight = FontWeight.Bold,
        )

        Button(
            onClick = onRetry,
            modifier =
                Modifier
                    .padding(8.dp)
                    .height(48.dp)
                    .fillMaxWidth(),
            colors =
                ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.errorContainer,
                    contentColor = MaterialTheme.colorScheme.error,
                ),
            shape = RoundedCornerShape(8.dp),
        ) {
            Text(text = "Retry")
        }
    }
}
