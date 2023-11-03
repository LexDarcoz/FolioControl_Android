package foliocontrol.android.foliocontrolandroid.components

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material.icons.outlined.Search
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
import foliocontrol.android.foliocontrolandroid.viewModels.AuthViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomNavigation(authViewModel: AuthViewModel) {
    var partnershipList = authViewModel.partnershipList
    Log.i("NAVBAR", "PARTNERSHIPLIST: $partnershipList ")
    data class BottomNavigationItem(
        val title: String,
        val selectedIcon: ImageVector,
        val unselectedIcon: ImageVector,
        val onClick: () -> Unit

    )

    var selectedIcon by rememberSaveable {
        mutableStateOf(0)
    }
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
            title = "Partnerships",
            selectedIcon = Icons.Filled.Menu,
            unselectedIcon = Icons.Outlined.Menu,
            onClick = { }

        )
    )

    NavigationBar(
        contentColor = MaterialTheme.colorScheme.primary,
        containerColor = MaterialTheme.colorScheme.primary
    ) {
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                selected =
                selectedIcon == index,
                onClick = {
                    if (item.title != "Partnerships") {
                        selectedIcon = index
                        authViewModel.navigateTo(item.title)
                    }
                },
                label = {
                    Text(text = item.title, color = MaterialTheme.colorScheme.secondary)
                },
                alwaysShowLabel = item.title == "Partnerships",
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
