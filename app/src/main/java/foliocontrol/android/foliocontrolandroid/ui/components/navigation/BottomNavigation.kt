package foliocontrol.android.foliocontrolandroid.components

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Apartment
import androidx.compose.material.icons.filled.Domain
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Apartment
import androidx.compose.material.icons.outlined.Domain
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import foliocontrol.android.foliocontrolandroid.ui.viewModels.AuthViewModel
import foliocontrol.android.foliocontrolandroid.ui.viewModels.PropertyViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun BottomNavigation(propertyViewModel: PropertyViewModel, authViewModel: AuthViewModel) {
    var partnershipList = propertyViewModel.partnershipListState
    var currentPartnership = propertyViewModel.currentPartnership

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
            selectedIcon = Icons.Filled.Domain,
            unselectedIcon = Icons.Outlined.Domain,
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
            selectedIcon = Icons.Filled.Apartment,
            unselectedIcon = Icons.Outlined.Apartment,
            onClick = { }

        )

    )

    val isPartnershipsSelected =
        selectedIcon == items.indexOfFirst { it.title == currentPartnership.name }

    NavigationBar(
        contentColor = MaterialTheme.colorScheme.onPrimary,
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
                    Text(text = item.title, color = MaterialTheme.colorScheme.onPrimary)
                },
                alwaysShowLabel = item.title == currentPartnership.name,
                icon = {
                    Icon(
                        imageVector = if (selectedIcon == index) item.selectedIcon else item.unselectedIcon, // ktlint-disable max-line-length
                        contentDescription = item.title,
                        tint = MaterialTheme.colorScheme.onPrimary

                    )
                }

            )
        }
        if (isPartnershipsSelected) {
            DropdownMenu(
                modifier = Modifier.background(MaterialTheme.colorScheme.surface),
                expanded = true,
                onDismissRequest = {
                    selectedIcon = -1
                },
                offset = DpOffset(x = 256.dp, y = (-50).dp)
            ) {
                partnershipList.forEach { partnership ->
                    if (partnership.name == currentPartnership.name) {
                        DropdownMenuItem(
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Filled.Apartment,
                                    contentDescription = null,
                                    tint = MaterialTheme.colorScheme.primary
                                )
                            },

                            text = {
                                Text(
                                    text = partnership.name,
                                    style = MaterialTheme.typography.titleMedium,
                                    color = MaterialTheme.colorScheme.primary
                                )
                            },
                            onClick = {
                                selectedIcon = 0
                            }
                        )
                    } else {
                        DropdownMenuItem(
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Outlined.Apartment,
                                    contentDescription = null,
                                    tint = MaterialTheme.colorScheme.primary
                                )
                            },

                            text = {
                                Text(
                                    text = partnership.name,
                                    style = MaterialTheme.typography.titleMedium,
                                    color = MaterialTheme.colorScheme.onSurface
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
}
