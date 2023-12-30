package foliocontrol.android.foliocontrolandroid.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Apartment
import androidx.compose.material.icons.outlined.AccountBox
import androidx.compose.material.icons.outlined.Apartment
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.zIndex
import foliocontrol.android.foliocontrolandroid.domain.Partnership
import foliocontrol.android.foliocontrolandroid.domain.User
import foliocontrol.android.foliocontrolandroid.ui.screens.account.AccountDetailScreen
import foliocontrol.android.foliocontrolandroid.ui.screens.account.AccountPartnershipScreen
import foliocontrol.android.foliocontrolandroid.ui.viewModels.common.ErrorScreen
import foliocontrol.android.foliocontrolandroid.ui.viewModels.common.LoadingScreen
import foliocontrol.android.foliocontrolandroid.ui.viewModels.common.UiState

val tabItems = listOf(
    TabItem("Details", Icons.Outlined.AccountBox, Icons.Filled.AccountBox),
    TabItem(
        "Partnerships",
        Icons.Outlined.Apartment,
        Icons.Filled.Apartment,
    ),
)

data class TabItem(
    val title: String,
    val unselectedIcon: ImageVector,
    val selectedIcon: ImageVector,
)

/**
 * Composable function representing the account screen of the Folio Control Android application.
 * It displays the user's account details and partnerships, allowing users to edit and save their information.
 *
 * @param userState The current state of the user, including user details.
 * @param handleUserSave A callback function to handle saving user information.
 * @param handleUserNameEdit A callback function to handle changes to the user's first name.
 * @param handleUserLastNameEdit A callback function to handle changes to the user's last name.
 * @param handleUserEmailEdit A callback function to handle changes to the user's email.
 * @param handleUserStreetEdit A callback function to handle changes to the user's street address.
 * @param handleUserStreetNumberEdit A callback function to handle changes to the user's street number.
 * @param handleUserZipCodeEdit A callback function to handle changes to the user's ZIP code.
 * @param handleUserCityEdit A callback function to handle changes to the user's city.
 * @param handleUserCountryEdit A callback function to handle changes to the user's country.
 * @param partnershipList The list of partnerships associated with the user.
 * @param getData A callback function to retrieve data.
 * @param uiState The current state of the UI.
 * @param navigateTo A callback function to navigate to a specific destination.
 */
@Composable
fun AccountScreen(
    userState: User,
    handleUserSave: () -> Unit,
    handleUserNameEdit: (String) -> Unit,
    handleUserLastNameEdit: (String) -> Unit,
    handleUserEmailEdit: (String) -> Unit,
    handleUserStreetEdit: (String) -> Unit,
    handleUserStreetNumberEdit: (String) -> Unit,
    handleUserZipCodeEdit: (String) -> Unit,
    handleUserCityEdit: (String) -> Unit,
    handleUserCountryEdit: (String) -> Unit,
    partnershipList: List<Partnership>,
    getData: () -> Unit = { },
    uiState: UiState,
    navigateTo: (Any?) -> Unit = {},
) {
    DisposableEffect(userState.userID) {
        getData()
        onDispose { }
    }
    when (uiState) {
        is UiState.LoggedOut -> {
        }

        is UiState.Success -> {
            AccountOverview(
                userState = userState,
                handleUserSave = handleUserSave,
                handleUserNameEdit = handleUserNameEdit,
                handleUserLastNameEdit = handleUserLastNameEdit,
                handleUserEmailEdit = handleUserEmailEdit,
                handleUserStreetEdit = handleUserStreetEdit,
                handleUserStreetNumberEdit = handleUserStreetNumberEdit,
                handleUserZipCodeEdit = handleUserZipCodeEdit,
                handleUserCityEdit = handleUserCityEdit,
                handleUserCountryEdit = handleUserCountryEdit,
                offline = false,
                partnershipList = partnershipList,
                navigateTo = navigateTo,
            )
        }

        is UiState.Offline -> {
            AccountOverview(
                userState = userState,
                handleUserSave = handleUserSave,
                handleUserNameEdit = handleUserNameEdit,
                handleUserLastNameEdit = handleUserLastNameEdit,
                handleUserEmailEdit = handleUserEmailEdit,
                handleUserStreetEdit = handleUserStreetEdit,
                handleUserStreetNumberEdit = handleUserStreetNumberEdit,
                handleUserZipCodeEdit = handleUserZipCodeEdit,
                handleUserCityEdit = handleUserCityEdit,
                handleUserCountryEdit = handleUserCountryEdit,
                offline = true,
                partnershipList = partnershipList,
                navigateTo = navigateTo,
            )
        }

        is UiState.Loading -> {
            LoadingScreen()
        }

        else -> {
            ErrorScreen(errorMessage = (uiState as UiState.Error).message, onRetry = { getData() })
        }
    }
}

/**
 * Composable function representing the overview of the user's account details and partnerships.
 * It includes a tab-based interface to switch between the "Details" and "Partnerships" views.
 *
 * @param userState The current state of the user, including user details.
 * @param handleUserSave A callback function to handle saving user information.
 * @param handleUserNameEdit A callback function to handle changes to the user's first name.
 * @param handleUserLastNameEdit A callback function to handle changes to the user's last name.
 * @param handleUserEmailEdit A callback function to handle changes to the user's email.
 * @param handleUserStreetEdit A callback function to handle changes to the user's street address.
 * @param handleUserStreetNumberEdit A callback function to handle changes to the user's street number.
 * @param handleUserZipCodeEdit A callback function to handle changes to the user's ZIP code.
 * @param handleUserCityEdit A callback function to handle changes to the user's city.
 * @param handleUserCountryEdit A callback function to handle changes to the user's country.
 * @param partnershipList The list of partnerships associated with the user.
 * @param offline A flag indicating whether the application is in offline mode.
 * @param navigateTo A callback function to navigate to a specific destination.
 */
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AccountOverview(
    userState: User,
    handleUserSave: () -> Unit,
    handleUserNameEdit: (String) -> Unit,
    handleUserLastNameEdit: (String) -> Unit,
    handleUserEmailEdit: (String) -> Unit,
    handleUserStreetEdit: (String) -> Unit,
    handleUserStreetNumberEdit: (String) -> Unit,
    handleUserZipCodeEdit: (String) -> Unit,
    handleUserCityEdit: (String) -> Unit,
    handleUserCountryEdit: (String) -> Unit,
    partnershipList: List<Partnership>,
    offline: Boolean,
    navigateTo: (Any?) -> Unit = {},
) {
    val pagerState = rememberPagerState {
        tabItems.size
    }
    var selectedTabIndex by remember {
        mutableStateOf(0)
    }

    LaunchedEffect(selectedTabIndex) {
        pagerState.animateScrollToPage(selectedTabIndex)
    }
    LaunchedEffect(pagerState.currentPage) {
        selectedTabIndex = pagerState.currentPage
    }
    Column {
        TabRow(
            selectedTabIndex = selectedTabIndex,
            containerColor = MaterialTheme.colorScheme.onPrimary,
        ) {
            tabItems.forEachIndexed { index, item ->
                Tab(selected = selectedTabIndex == index, onClick = {
                    selectedTabIndex = index
                }, text = {
                    Text(text = item.title, color = MaterialTheme.colorScheme.primary)
                }, icon = {
                    @Suppress("ktlint:standard:max-line-length") (if (selectedTabIndex == index) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSecondary).let {
                        Icon(
                            tint = it,
                            imageVector = if (selectedTabIndex == index) item.selectedIcon else item.unselectedIcon,
                            contentDescription = item.title,
                        )
                    }
                })
            }
        }
        HorizontalPager(
            state = pagerState,
            Modifier
                .fillMaxWidth()
                .zIndex(1f),
        ) { index ->
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                if (index == 0) {
                    AccountDetailScreen(
                        userState = userState,
                        handleUserSave = handleUserSave,
                        handleUserNameEdit = handleUserNameEdit,
                        handleUserLastNameEdit = handleUserLastNameEdit,
                        handleUserEmailEdit = handleUserEmailEdit,
                        handleUserStreetEdit = handleUserStreetEdit,
                        handleUserStreetNumberEdit = handleUserStreetNumberEdit,
                        handleUserZipCodeEdit = handleUserZipCodeEdit,
                        handleUserCityEdit = handleUserCityEdit,
                        handleUserCountryEdit = handleUserCountryEdit,
                        offline,
                        navigateTo,
                    )
                } else if (index == 1) {
                    AccountPartnershipScreen(
                        partnershipList = partnershipList,
                    )
                }
            }
        }
    }
}
