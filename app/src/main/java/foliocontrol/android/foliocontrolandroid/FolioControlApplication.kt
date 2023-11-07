package foliocontrol.android.foliocontrolandroid

import PropertyDetailScreen
import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import foliocontrol.android.foliocontrolandroid.components.BottomNavigation
import foliocontrol.android.foliocontrolandroid.components.Navbar
import foliocontrol.android.foliocontrolandroid.domain.viewModels.AuthViewModel
import foliocontrol.android.foliocontrolandroid.domain.viewModels.LoginUiState
import foliocontrol.android.foliocontrolandroid.domain.viewModels.PropertyViewModel
import foliocontrol.android.foliocontrolandroid.screens.AccountScreen
import foliocontrol.android.foliocontrolandroid.screens.AuthScreen
import foliocontrol.android.foliocontrolandroid.screens.SearchScreen
import foliocontrol.android.foliocontrolandroid.screens.SettingScreen

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FolioControlApplication(
    authViewModel: AuthViewModel = viewModel(factory = AppViewModelProvider.Factory),
    propertyViewModel: PropertyViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())
    val navController = rememberNavController()
    Scaffold(
        modifier = Modifier.fillMaxWidth(),
        contentColor = MaterialTheme.colorScheme.primary,
        containerColor = MaterialTheme.colorScheme.background,
        bottomBar = {
            when (authViewModel.loginUiState) {
                is LoginUiState.Success -> {
                    BottomNavigation(authViewModel = authViewModel)
                }

                else -> {
                }
            }
        },
        topBar = {
            Navbar(scrollBehavior, authViewModel = authViewModel, navController = navController)
        }

    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            AppNavigator(
                navController = navController,
                authViewModel = authViewModel,
                propertyViewModel = propertyViewModel
            )
        }
    }
}

@Composable
fun AppNavigator(
    navController: NavHostController,
    authViewModel: AuthViewModel,
    propertyViewModel: PropertyViewModel
) {
    authViewModel.navigateTo = {
        navController.navigate(it)
    }
    NavHost(navController = navController, startDestination = "Home") {
        // Auth
        composable("Home") {
            AuthScreen(authViewModel = authViewModel, propertyViewModel = propertyViewModel) {
                navController.navigate(
                    "PropertyDetail/$it"
                )
            }
        }
        // Main
        composable("Account") { AccountScreen() }
        composable("Settings") { SettingScreen(/*...*/) }
        composable("Search") { SearchScreen(/*...*/) }
        // Portfolio
        composable(
            "PropertyDetail/{id}",
            arguments = listOf(
                navArgument("id") { type = NavType.IntType }

            )
        ) { PropertyDetailScreen(/*...*/) }
    }
}
