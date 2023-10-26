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
fun FolioControlApplication(viewModel: AuthViewModel) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())
    val navController = rememberNavController()
    viewModel.navigateTo = {
        navController.navigate(it)
    }
    Scaffold(
        modifier = Modifier.fillMaxWidth(),
        contentColor = MaterialTheme.colorScheme.primary,
        containerColor = MaterialTheme.colorScheme.secondary,
        bottomBar = {
            if (viewModel.loginUiState is LoginUiState.Success) {
                BottomNavigation(viewModel = viewModel)
            }
        },
        topBar = {
            Navbar(scrollBehavior, viewModel = viewModel)
        }

    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            AppNavigator(viewModel = viewModel, navController = navController)
        }
    }
}

@Composable
fun AppNavigator(viewModel: AuthViewModel, navController: NavHostController) {
    NavHost(navController = navController, startDestination = "Login") {
        // Auth
        composable("AuthScreen") { AuthScreen(loginUiState = viewModel.loginUiState) }
        // Main
        composable("Home") { HomeScreen(/*...*/) }
        composable("Account") { AccountScreen() }
        composable("Settings") { SettingScreen(/*...*/) }
        composable("Search") { SearchScreen(/*...*/) }
    }
}
