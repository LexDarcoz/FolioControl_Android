package foliocontrol.android.foliocontrolandroid

import android.annotation.SuppressLint
import android.app.Activity
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
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import foliocontrol.android.foliocontrol_android.R
import foliocontrol.android.foliocontrolandroid.components.BottomNavigation
import foliocontrol.android.foliocontrolandroid.components.Navbar
import foliocontrol.android.foliocontrolandroid.screens.AccountScreen
import foliocontrol.android.foliocontrolandroid.screens.AuthScreen
import foliocontrol.android.foliocontrolandroid.ui.screens.portfolio.PropertyOverviewScreen
import foliocontrol.android.foliocontrolandroid.ui.viewModels.AccountViewModel
import foliocontrol.android.foliocontrolandroid.ui.viewModels.AuthViewModel
import foliocontrol.android.foliocontrolandroid.ui.viewModels.PropertyViewModel
import foliocontrol.android.foliocontrolandroid.ui.viewModels.common.UiState

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
    Scaffold(modifier = Modifier.fillMaxWidth(),
        contentColor = MaterialTheme.colorScheme.primary,
        contentWindowInsets = WindowInsets(left = 0.dp, top = 0.dp, right = 0.dp, bottom = 0.dp),
        bottomBar = {
            when (authViewModel.loginUiState) {
                is UiState.Success -> {
                    BottomNavigation(

                        authViewModel = authViewModel, propertyViewModel = propertyViewModel
                    )
                }

                else -> {
                }
            }
        },
        topBar = {
            Navbar(
                scrollBehavior,
                authViewModel = authViewModel,
                navController = navController,
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
        navController.navigate(it)
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
