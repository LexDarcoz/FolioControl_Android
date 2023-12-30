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
    confirmText: String,
    dismissText: String,
    dialogText: String,
    icon: ImageVector,
) {
    AlertDialog(containerColor = MaterialTheme.colorScheme.primary, icon = {
        Icon(
            icon,
            contentDescription = "Error Icon",
            tint = MaterialTheme.colorScheme.error,
        )
    }, title = {
        Text(text = dialogTitle, color = MaterialTheme.colorScheme.secondary)
    }, text = {
        Text(text = dialogText, color = MaterialTheme.colorScheme.secondary)
    }, onDismissRequest = {
        onDismissRequest()
    }, confirmButton = {
        TextButton(onClick = {
            onConfirmation()
        }) {
            Text(confirmText, color = MaterialTheme.colorScheme.secondary)
        }
    }, dismissButton = {
        TextButton(onClick = {
            onDismissRequest()
        }) {
            Text(dismissText, color = MaterialTheme.colorScheme.secondary)
        }
    })
}
