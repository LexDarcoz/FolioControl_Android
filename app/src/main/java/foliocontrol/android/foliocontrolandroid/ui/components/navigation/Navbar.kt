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
import androidx.navigation.NavHostController
import foliocontrol.android.foliocontrolandroid.ui.viewModels.AuthViewModel
import foliocontrol.android.foliocontrolandroid.ui.viewModels.common.UiState
import foliocontrol.android.foliocontrolandroid.ui.viewModels.common.WindowInfo
import foliocontrol.android.foliocontrolandroid.ui.viewModels.common.rememberWindowInfo

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Navbar(
    scrollBehavior: TopAppBarScrollBehavior,
    loginUiState: UiState,
    logOut: () -> Unit,
    navigateTo: (String) -> Unit,
    navigateUp: () -> Unit,
    toggleDrawer: () -> Unit
) {
    val windowInfo = rememberWindowInfo()
    CenterAlignedTopAppBar(colors = TopAppBarDefaults.largeTopAppBarColors(
        containerColor = MaterialTheme.colorScheme.primary,
        titleContentColor = MaterialTheme.colorScheme.secondary
    ), title = {
        Text(
            "FolioControl",
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            color = MaterialTheme.colorScheme.onPrimary
        )
    }, navigationIcon = {
        if (windowInfo.screenWidthInfo is WindowInfo.WindowType.Compact) {
            IconButton(onClick = { navigateUp() }) {
                Icon(
                    tint = MaterialTheme.colorScheme.onPrimary,
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "Back"
                )
            }
        } else {
            IconButton(onClick = { toggleDrawer() }) {
                Icon(
                    tint = MaterialTheme.colorScheme.onPrimary,
                    imageVector = Icons.Filled.Menu,
                    contentDescription = "Open Navigation Menu"
                )
            }
        }
    },

        actions = {
            when (loginUiState) {
                is UiState.Success -> {
                    IconButton(onClick = {
                        logOut()
                        navigateTo("Home")
                    }) {
                        Icon(
                            imageVector = Icons.Filled.ExitToApp,
                            contentDescription = "Log out",
                            tint = MaterialTheme.colorScheme.onPrimary
                        )
                    }
                }

                else -> {
                }
            }
        },

        scrollBehavior = scrollBehavior
    )
}
