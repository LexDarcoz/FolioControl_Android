package foliocontrol.android.foliocontrolandroid.components

import android.annotation.SuppressLint
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.lifecycle.viewmodel.compose.viewModel

import foliocontrol.android.foliocontrolandroid.AppViewModelProvider
import foliocontrol.android.foliocontrolandroid.context.AuthViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomNavigation(authViewModel: AuthViewModel ){
    data class BottomNavigationItem(
        val title: String,
        val selectedIcon: ImageVector,
        val unselectedIcon: ImageVector,
        val onClick: () -> Unit

    )

    val items = listOf(
        BottomNavigationItem(
            title = "Home",
            selectedIcon = Icons.Filled.Home,
            unselectedIcon = Icons.Outlined.Home,
            onClick = { }
        ),
        BottomNavigationItem(
            title = "Search",
            selectedIcon = Icons.Filled.Search,
            unselectedIcon = Icons.Outlined.Search,
            onClick = { }

        ),
        BottomNavigationItem(
            title = "Account",
            selectedIcon = Icons.Filled.AccountCircle,
            unselectedIcon = Icons.Outlined.AccountCircle,
            onClick = { }

        ),
        BottomNavigationItem(
            title = "Settings",
            selectedIcon = Icons.Filled.Settings,
            unselectedIcon = Icons.Outlined.Settings,
            onClick = { }

        )
    )
    var selectedIcon by rememberSaveable {
        mutableStateOf(0)
    }
    NavigationBar(
        contentColor = MaterialTheme.colorScheme.primary,
        containerColor = MaterialTheme.colorScheme.primary
    ) {
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                selected = selectedIcon == index,
                onClick = {
                    selectedIcon = index
                    authViewModel.navigateTo(item.title)
                },
                label = {
                    Text(text = item.title, color = MaterialTheme.colorScheme.secondary)
                },
                alwaysShowLabel = false,
                icon = {
                    Icon(
                        imageVector = if (selectedIcon == index) item.selectedIcon else item.unselectedIcon, // ktlint-disable max-line-length
                        contentDescription = item.title,
                        tint = MaterialTheme.colorScheme.secondary

                    )
                }

            )
        }
    }
}
