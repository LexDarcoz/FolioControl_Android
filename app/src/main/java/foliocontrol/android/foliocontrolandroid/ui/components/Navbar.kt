package foliocontrol.android.foliocontrolandroid.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextOverflow
import androidx.navigation.NavHostController
import foliocontrol.android.foliocontrolandroid.viewModels.AuthViewModel
import foliocontrol.android.foliocontrolandroid.viewModels.LoginUiState


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Navbar(
    scrollBehavior: TopAppBarScrollBehavior,
    authViewModel: AuthViewModel,
    navController: NavHostController
) {
    CenterAlignedTopAppBar(colors = TopAppBarDefaults.largeTopAppBarColors(
        containerColor = MaterialTheme.colorScheme.primary,
        titleContentColor = MaterialTheme.colorScheme.secondary
    ), title = {
        Text(
            "FolioControl",
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            color = MaterialTheme.colorScheme.secondary
        )


    }, navigationIcon = {


        IconButton(onClick = { navController.navigateUp() }) {
            Icon(
                tint = MaterialTheme.colorScheme.secondary,
                imageVector = Icons.Filled.ArrowBack,
                contentDescription = "Back"
            )
        }
    },

        actions = {
            when (authViewModel.loginUiState) {
                is LoginUiState.Success -> {
                    IconButton(onClick = {
                        authViewModel.logOut()
                        navController.navigate("Home")

                    }) {
                        Icon(
                            imageVector = Icons.Filled.ExitToApp,
                            contentDescription = "Localized description",
                            tint = MaterialTheme.colorScheme.secondary
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
