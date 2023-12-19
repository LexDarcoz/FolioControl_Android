package foliocontrol.android.foliocontrolandroid.ui.components.foliocomponents

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
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
    onItemSelect: (String) -> Unit = {}

) {
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
            IconButton(onClick = { toggleExpanded() }) {
                Icon(Icons.Default.MoreVert, contentDescription = "Localized description")
            }
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
                        },
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }
    }
}
