package foliocontrol.android.foliocontrolandroid.ui.components.dialogs

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector

@Composable
fun DeleteDialog(
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
    dialogTitle: String,
    dialogText: String,
    icon: ImageVector
) {
    AlertDialog(
        icon = {
            Icon(
                icon,
                contentDescription = "Example Icon",
                tint = MaterialTheme.colorScheme.error
            )
        },
        title = {
            Text(text = dialogTitle, color = MaterialTheme.colorScheme.secondary)
        },
        text = {
            Text(text = dialogText, color = MaterialTheme.colorScheme.secondary)
        },
        onDismissRequest = {
            onDismissRequest()
        },
        confirmButton = {
            TextButton(
                onClick = {
                    onConfirmation()
                }
            ) {
                Text("Confirm", color = MaterialTheme.colorScheme.secondary)
            }
        },
        dismissButton = {
            TextButton(
                onClick = {
                    onDismissRequest()
                }
            ) {
                Text("Dismiss", color = MaterialTheme.colorScheme.secondary)
            }
        }
    )
}
