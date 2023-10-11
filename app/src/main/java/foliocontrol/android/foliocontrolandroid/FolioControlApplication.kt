package foliocontrol.android.foliocontrolandroid

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import foliocontrol.android.foliocontrolandroid.components.BottomNavigation
import foliocontrol.android.foliocontrolandroid.components.Navbar
import foliocontrol.android.foliocontrolandroid.screens.AccountScreen
import foliocontrol.android.foliocontrolandroid.screens.HomeScreen
import foliocontrol.android.foliocontrolandroid.screens.LoginScreen
import foliocontrol.android.foliocontrolandroid.screens.SearchScreen
import foliocontrol.android.foliocontrolandroid.screens.SettingScreen


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FolioControlApplication() {

    val navController = rememberNavController()
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())


    Scaffold(
        modifier = Modifier.fillMaxWidth(),
        contentColor = MaterialTheme.colorScheme.primary,
        containerColor = MaterialTheme.colorScheme.secondary,
        bottomBar = {
            BottomNavigation(navController)
        },
        topBar = {
            Navbar(scrollBehavior)
        },


        ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            NavHost(navController = navController, startDestination = "Login") {
                composable("Login") { LoginScreen(navController) }
                composable("Home") { HomeScreen(/*...*/) }
                composable("Account") { AccountScreen() }
                composable("Settings") { SettingScreen(/*...*/) }
                composable("Search") { SearchScreen(/*...*/) }
            }
        }
    }

}

