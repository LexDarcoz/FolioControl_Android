package foliocontrol.android.foliocontrolandroid

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
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import foliocontrol.android.foliocontrolandroid.components.BottomNavigation
import foliocontrol.android.foliocontrolandroid.components.Navbar
import foliocontrol.android.foliocontrolandroid.context.AuthViewModel
import foliocontrol.android.foliocontrolandroid.context.LoginUiState
import foliocontrol.android.foliocontrolandroid.screens.AccountScreen
import foliocontrol.android.foliocontrolandroid.screens.AuthScreen
import foliocontrol.android.foliocontrolandroid.screens.HomeScreen
import foliocontrol.android.foliocontrolandroid.screens.SearchScreen
import foliocontrol.android.foliocontrolandroid.screens.SettingScreen

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FolioControlApplication(
    authViewModel: AuthViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())
    val navController = rememberNavController()
    authViewModel.navigateTo = {
        navController.navigate(it)
    }
    Scaffold(
        modifier = Modifier.fillMaxWidth(),
        contentColor = MaterialTheme.colorScheme.primary,
        containerColor = MaterialTheme.colorScheme.secondary,
        bottomBar = {
            if (authViewModel.loginUiState is LoginUiState.Success) {
                BottomNavigation(viewModel = authViewModel)
            }
        },
        topBar = {
            Navbar(scrollBehavior, viewModel = authViewModel)
        }

    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            AppNavigator(navController = navController)
        }
    }
}

@Composable
fun AppNavigator(
    navController: NavHostController
) {
    NavHost(navController = navController, startDestination = "AuthScreen") {
        // Auth
        composable("AuthScreen") {
            AuthScreen()
        }
        // Main
        composable("Home") { HomeScreen(/*...*/) }
        composable("Account") { AccountScreen() }
        composable("Settings") { SettingScreen(/*...*/) }
        composable("Search") { SearchScreen(/*...*/) }
    }
}
