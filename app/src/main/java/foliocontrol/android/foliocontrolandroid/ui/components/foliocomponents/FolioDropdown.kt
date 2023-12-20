package foliocontrol.android.foliocontrolandroid.ui.components.foliocomponents

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class DropDownMenuItem(
    val text: String,
    val icon: @Composable () -> Unit,
    val description: String
)

@Composable
fun FolioDropdown(

    expanded: Boolean,
    toggleExpanded: () -> Unit,
    items: List<DropDownMenuItem>,
    label: String,
    onItemSelect: (String) -> Unit = {},
    initialValue: String = ""
) {
    var selected by remember { mutableStateOf(initialValue) }
    Column {
        Text(
            text = label,
            style = MaterialTheme.typography.bodyLarge.copy(
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.primary
            ),
            modifier = Modifier.padding(bottom = 4.dp)
        )

        Box(
            modifier = Modifier.fillMaxWidth().background(MaterialTheme.colorScheme.secondary)
                .height(50.dp)
        ) {
            OutlinedTextField(
                value = selected,
                onValueChange = { },
                enabled = false,
                textStyle = MaterialTheme.typography.bodySmall.copy(
                    color = MaterialTheme.colorScheme.onSurface
                ),
                modifier = Modifier.fillMaxWidth().clickable { toggleExpanded() },
                trailingIcon = {
                    Icon(
                        Icons.Default.MoreVert,
                        contentDescription = "Localized description"
                    )
                },
                colors = OutlinedTextFieldDefaults.colors(
                    disabledTextColor = MaterialTheme.colorScheme.onSurface, // Set the text color for disabled state
                    disabledBorderColor = MaterialTheme.colorScheme.onSurface // Set the border color for disabled state
                )

            )

            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { toggleExpanded() },
                modifier = Modifier.fillMaxWidth()
            ) {
                items.forEach { item ->
                    DropdownMenuItem(
                        text = { Text(text = item.text) },
                        leadingIcon = { item.icon },
                        onClick = {
                            onItemSelect(item.text)
                            selected = item.text
                            toggleExpanded()
                        },
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }
    }
}
