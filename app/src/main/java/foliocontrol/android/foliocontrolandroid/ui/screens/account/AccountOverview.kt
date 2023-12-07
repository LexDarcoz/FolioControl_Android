package foliocontrol.android.foliocontrolandroid.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.List
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
import foliocontrol.android.foliocontrolandroid.ui.screens.account.AccountDetailScreen
import foliocontrol.android.foliocontrolandroid.ui.screens.account.AccountPartnershipScreen
import foliocontrol.android.foliocontrolandroid.ui.viewModels.AccountViewModel
import foliocontrol.android.foliocontrolandroid.ui.viewModels.common.ErrorScreen
import foliocontrol.android.foliocontrolandroid.ui.viewModels.common.LoadingScreen
import foliocontrol.android.foliocontrolandroid.ui.viewModels.common.UiState

val tabItems = listOf(
    TabItem("Details", Icons.Outlined.Home, Icons.Filled.Home),
    TabItem("Partnerships", Icons.Outlined.List, Icons.Filled.List)
)

data class TabItem(
    val title: String,
    val unselectedIcon: ImageVector,
    val selectedIcon: ImageVector
)

@Composable
fun AccountScreen(accountViewModel: AccountViewModel, navigateTo: (Any?) -> Unit = {}) {
    DisposableEffect(accountViewModel) {
        if (accountViewModel.user.name.isEmpty()) {
            accountViewModel.getData()
        }
        onDispose { }
    }
    when (accountViewModel.uiState) {
        is UiState.LoggedOut -> {
            navigateTo("home")
        }

        is UiState.Success -> {
            AccountOverview(accountViewModel, navigateTo)
        }

        is UiState.Loading -> {
            LoadingScreen()
        }

        else -> {
            ErrorScreen(
                errorMessage = (accountViewModel.uiState as UiState.Error).message,
                onRetry = { accountViewModel.getData() }
            )
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AccountOverview(
    accountViewModel: AccountViewModel,
    navigateTo: (Any?) -> Unit = {}
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
        TabRow(selectedTabIndex = selectedTabIndex) {
            tabItems.forEachIndexed { index, item ->
                Tab(selected = selectedTabIndex == index, onClick = {
                    selectedTabIndex = index
                }, text = {
                    Text(text = item.title, color = MaterialTheme.colorScheme.secondary)
                }, icon = {
                    (if (selectedTabIndex == index) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.secondary)?.let { // ktlint-disable max-line-length
                        Icon(
                            tint = it,
                            imageVector = if (selectedTabIndex == index) item.selectedIcon else item.unselectedIcon, // ktlint-disable max-line-length
                            contentDescription = item.title
                        )
                    }
                })
            }
        }
        HorizontalPager(
            state = pagerState,
            Modifier
                .fillMaxWidth()
                .zIndex(1f)
        ) { index ->
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                if (index == 0) {
                    AccountDetailScreen(accountViewModel, navigateTo)
                } else if (index == 1) AccountPartnershipScreen(accountViewModel, navigateTo)
            }
        }
    }
}
