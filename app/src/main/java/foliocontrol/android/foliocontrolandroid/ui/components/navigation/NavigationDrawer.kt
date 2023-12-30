import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Apartment
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Apartment
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Surface
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
import androidx.navigation.NavHostController
import foliocontrol.android.foliocontrolandroid.domain.Partnership


data class NavigationItem(
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
)

@Composable
fun NavigationDrawer(
    drawerState: DrawerState,
    toggleDrawer: () -> Unit,
    navController: NavHostController,
    currentPartnership: Partnership,
    partnershipList: List<Partnership>,
    switchPartnership: (Partnership) -> Unit,
    navigateTo: (String) -> Unit,
    content: @Composable (Modifier) -> Unit
) {
    val items = listOf(
        NavigationItem(
            title = "Home",
            selectedIcon = Icons.Filled.Home,
            unselectedIcon = Icons.Outlined.Home,
        ),
        NavigationItem(
            title = "Account",
            selectedIcon = Icons.Filled.AccountCircle,
            unselectedIcon = Icons.Outlined.AccountCircle,
        ),
        NavigationItem(
            title = currentPartnership.name,
            selectedIcon = Icons.Filled.Apartment,
            unselectedIcon = Icons.Outlined.Apartment,
        ),
    )

    Surface(
        modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
    ) {
        var selectedItemIndex by rememberSaveable {
            mutableStateOf(0)
        }
        val isPartnershipsSelected =
            selectedItemIndex == items.indexOfFirst { it.title == currentPartnership.name }

        ModalNavigationDrawer(
            drawerContent = {

                ModalDrawerSheet(
                    drawerContentColor = MaterialTheme.colorScheme.primary,
                    drawerContainerColor = MaterialTheme.colorScheme.primary,
                ) {
                    Spacer(modifier = Modifier.height(16.dp))
                    items.forEachIndexed { index, item ->
                        NavigationDrawerItem(
                            label = {
                                Text(text = item.title, color = MaterialTheme.colorScheme.primary)
                            },
                            selected = index == selectedItemIndex,
                            onClick = {
                                selectedItemIndex = index
                                if (item.title != currentPartnership.name) {
                                    navController.navigate(item.title)
                                    toggleDrawer()
                                }

                            },
                            icon = {
                                Icon(
                                    imageVector = if (index == selectedItemIndex) {
                                        item.selectedIcon
                                    } else item.unselectedIcon,
                                    contentDescription = item.title,
                                    tint = MaterialTheme.colorScheme.primary
                                )
                            },
                            modifier = Modifier.padding(8.dp),

                            )
                        if (index == 1)
                            Divider()
                    }
                    if (isPartnershipsSelected) {
                        DropdownMenu(
                            modifier = Modifier.background(MaterialTheme.colorScheme.surface),
                            expanded = true,
                            onDismissRequest = {
                                selectedItemIndex = -1
                            },
                            offset = DpOffset(x = 256.dp, y = (-50).dp)
                        ) {
                            partnershipList.forEach { partnership ->
                                if (partnership.name == currentPartnership.name) {
                                    DropdownMenuItem(leadingIcon = {
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
                                        }, onClick = {
                                            selectedItemIndex = 0
                                        })
                                } else {
                                    DropdownMenuItem(leadingIcon = {
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
                                        }, onClick = {
                                            switchPartnership(partnership)
                                            navigateTo("Home")
                                            selectedItemIndex = 0
                                        })
                                }
                            }
                        }
                    }
                }
            }, drawerState = drawerState
        ) {
            content(Modifier.fillMaxSize())
        }
    }
}


