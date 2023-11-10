package foliocontrol.android.foliocontrolandroid.components

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.offset
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
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
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import foliocontrol.android.foliocontrolandroid.ui.viewModels.AuthViewModel
import foliocontrol.android.foliocontrolandroid.ui.viewModels.PropertyViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun BottomNavigation(propertyViewModel: PropertyViewModel, authViewModel: AuthViewModel) {
    var partnershipList = propertyViewModel.partnershipList
    var currentPartnership = propertyViewModel.currentPartnership

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
            title = "Account",
            selectedIcon = Icons.Filled.AccountCircle,
            unselectedIcon = Icons.Outlined.AccountCircle,
            onClick = { }

        ),
        BottomNavigationItem(
            title = currentPartnership.name,
            selectedIcon = Icons.Filled.Menu,
            unselectedIcon = Icons.Outlined.Menu,
            onClick = { }

        )

    )
    Log.i("TESTING", "currentpartnership:${currentPartnership.name}  ")
    val isPartnershipsSelected =
        selectedIcon == items.indexOfFirst { it.title == currentPartnership.name }

    NavigationBar(
        contentColor = MaterialTheme.colorScheme.secondary,
        containerColor = MaterialTheme.colorScheme.primary
    ) {
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                selected = selectedIcon == index,
                onClick = {
                    selectedIcon = index
                    if (item.title != currentPartnership.name) {
                        authViewModel.navigateTo(item.title)
                    }
                },
                label = {
                    Text(text = item.title, color = MaterialTheme.colorScheme.secondary)
                },
                alwaysShowLabel = item.title == currentPartnership.name,
                icon = {
                    Icon(
                        imageVector = if (selectedIcon == index) item.selectedIcon else item.unselectedIcon, // ktlint-disable max-line-length
                        contentDescription = item.title,
                        tint = MaterialTheme.colorScheme.secondary

                    )
                }

            )
        }
        if (isPartnershipsSelected) {
            DropdownMenu(
//                modifier = Modifier // Set the desired offset here
// //                    .fillMaxWidth(),
                expanded = true,
                onDismissRequest = {
                    // Dismiss the dropdown when clicked outside
                    selectedIcon = -1
                },
                offset = DpOffset(x = 256.dp, y = (-50).dp)
            ) {
                partnershipList.forEach { partnership ->
                    DropdownMenuItem(
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Default.Home,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.secondary
                            )
                        },

                        text = {
                            Text(
                                text = partnership.name,
                                style = MaterialTheme.typography.titleMedium
                            )
                        },
                        onClick = {
                            propertyViewModel.switchPartnership(partnership)
                            authViewModel.navigateTo("Home")
                            selectedIcon = 0
                        }
                    )
                }
            }
        }
    }
}
