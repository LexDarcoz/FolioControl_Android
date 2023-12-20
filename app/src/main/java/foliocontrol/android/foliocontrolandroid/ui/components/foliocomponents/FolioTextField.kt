package foliocontrol.android.foliocontrolandroid.ui.components.foliocomponents

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp

@Composable
fun FolioTextField(
    enabled: Boolean = true,
    label: String,
    value: String,
    onValueChange: (String) -> Unit

) {
    Column() {
        Text(
            text = label,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.primary
        )
        OutlinedTextField(
            enabled = enabled,
            value = value,
            onValueChange = onValueChange,
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done
            ),

            keyboardActions = KeyboardActions(onDone = {
                // Handle done action
            }),
            textStyle = MaterialTheme.typography.bodySmall.copy(
                color = MaterialTheme.colorScheme.onSurface
            ),
            singleLine = true,
            modifier = Modifier.fillMaxWidth().background(MaterialTheme.colorScheme.secondary)
                .height(50.dp)

        )
    }
}
