package foliocontrol.android.foliocontrolandroid

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import foliocontrol.android.foliocontrolandroid.components.BottomNavigation
import foliocontrol.android.foliocontrolandroid.components.Navbar
import foliocontrol.android.foliocontrolandroid.screens.AccountScreen
import foliocontrol.android.foliocontrolandroid.screens.AuthScreen
import foliocontrol.android.foliocontrolandroid.ui.screens.portfolio.PropertyOverviewScreen
import foliocontrol.android.foliocontrolandroid.ui.viewModels.AccountViewModel
import foliocontrol.android.foliocontrolandroid.ui.viewModels.AuthViewModel
import foliocontrol.android.foliocontrolandroid.ui.viewModels.PropertyViewModel
import foliocontrol.android.foliocontrolandroid.ui.viewModels.common.UiState
import foliocontrol.android.foliocontrolandroid.ui.viewModels.common.WindowInfo
import foliocontrol.android.foliocontrolandroid.ui.viewModels.common.rememberWindowInfo

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FolioControlApplication(
    authViewModel: AuthViewModel = viewModel(factory = AppViewModelProvider.Factory),
    propertyViewModel: PropertyViewModel = viewModel(factory = AppViewModelProvider.Factory),
    accountViewModel: AccountViewModel = viewModel(factory = AppViewModelProvider.Factory),
    navController: NavHostController = rememberNavController()
) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())
    val windowInfo = rememberWindowInfo()
    Scaffold(
        modifier = Modifier.fillMaxWidth(),
        contentColor = MaterialTheme.colorScheme.primary,
        contentWindowInsets = WindowInsets(left = 0.dp, top = 0.dp, right = 0.dp, bottom = 0.dp),
        bottomBar = {
            when (authViewModel.loginUiState) {
                is UiState.Success -> {
                    if (windowInfo.screenWidthInfo is WindowInfo.WindowType.Compact) {
                        BottomNavigation(
                            authViewModel = authViewModel,
                            propertyViewModel = propertyViewModel
                        )
                    }
                }

                else -> {
                }
            }
        },
        topBar = {
            Navbar(
                scrollBehavior,
                authViewModel = authViewModel,
                navController = navController
            )
        }

    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            AppNavigator(
                navController = navController,
                authViewModel = authViewModel,
                propertyViewModel = propertyViewModel,
                accountViewModel = accountViewModel
            )
        }
    }
}

@Composable
fun AppNavigator(
    navController: NavHostController,
    authViewModel: AuthViewModel,
    propertyViewModel: PropertyViewModel,
    accountViewModel: AccountViewModel
) {
    authViewModel.navigateTo = {
        navController.navigate(it) {
            // Pop up to the start destination of the graph to
            // avoid building up a large stack of destinations
            popUpTo(navController.graph.startDestinationId) {
                saveState = true
            }
            // Avoid multiple copies of the same destination when
            // reselecting the same item
            launchSingleTop = true
            // Restore state when reselecting a previously selected item
            restoreState = true
        }
    }
    NavHost(navController = navController, startDestination = "Home") {
        // Auth
        composable("Home") {
            AuthScreen(authViewModel = authViewModel, propertyViewModel = propertyViewModel) {
                navController.navigate(
                    "$it"
                )
            }
        }
        // Account
        composable("Account") { AccountScreen(accountViewModel, propertyViewModel) }
        // Portfolio
        composable("PropertyDetail") {
            PropertyOverviewScreen(
                propertyViewModel
            ) { navController.navigate("$it") }
        }
    }
}
