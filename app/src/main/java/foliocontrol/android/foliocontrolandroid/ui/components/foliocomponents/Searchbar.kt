package foliocontrol.android.foliocontrolandroid.ui.components.foliocomponents

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp

@Composable
fun SearchBar(
    toggleSearchBar: () -> Unit,
    filterProperties: (String) -> Unit,
) {
    var query by remember { mutableStateOf("") }

    val searchBarBackgroundColor = MaterialTheme.colorScheme.background
    val contentColor = MaterialTheme.colorScheme.onSurface
    val borderColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.12f)
    val borderWidth = 2.dp
    val cornerRadius = 8.dp
    val iconTint = MaterialTheme.colorScheme.onSurface

    // Search icon
    val searchIcon = Icons.Default.Search

    Box(
        modifier =
            Modifier
                .fillMaxWidth()
                .background(searchBarBackgroundColor)
                .padding(16.dp),
    ) {
        // Search bar with border
        TextField(
            value = query,
            onValueChange = {
                query = it
            },
            modifier =
                Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .clip(RoundedCornerShape(cornerRadius))
                    .border(
                        width = borderWidth,
                        color = borderColor,
                        shape = RoundedCornerShape(cornerRadius),
                    ),
            textStyle = LocalTextStyle.current.copy(color = contentColor),
            placeholder = {
                Text(
                    text = "Search...",
                    color = contentColor.copy(alpha = 0.8f),
                )
            },
            leadingIcon = {
                Icon(
                    imageVector = searchIcon,
                    contentDescription = null,
                    tint = iconTint,
                )
            },
            trailingIcon = {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = null,
                    tint = iconTint,
                    modifier =
                        Modifier
                            .padding(end = 8.dp)
                            .clickable {
                                toggleSearchBar()
                            },
                )
            },
            keyboardOptions =
                KeyboardOptions(
                    imeAction = ImeAction.Search,
                ),
            keyboardActions =
                KeyboardActions(onSearch = {
                    filterProperties(query)
                }),
        )
    }
}
