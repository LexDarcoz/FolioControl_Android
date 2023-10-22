package foliocontrol.android.foliocontrolandroid.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.style.TextOverflow
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Navbar(scrollBehavior: TopAppBarScrollBehavior, navController: NavController) {

    CenterAlignedTopAppBar(
        
        colors = TopAppBarDefaults.largeTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.secondary,
        ),
        title = {
            Text(
                "FolioControl",
                maxLines = 1,
                overflow = TextOverflow.Ellipsis, color = MaterialTheme.colorScheme.secondary
            )
        },

        actions = {
            IconButton(onClick = {  navController.navigate("Login") }) {
                Icon(
                    imageVector = Icons.Filled.ExitToApp,
                    contentDescription = "Localized description",
                    tint = MaterialTheme.colorScheme.secondary
                )
            }
        },
        scrollBehavior = scrollBehavior,
    )

}