package foliocontrol.android.foliocontrolandroid.ui.screens.portfolio

import PropertyDetailScreen
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column


import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.outlined.Build
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.List
import androidx.compose.material.icons.outlined.Phone
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.zIndex
import foliocontrol.android.foliocontrolandroid.ui.viewModels.common.LoadingScreen

import foliocontrol.android.foliocontrolandroid.ui.viewModels.PropertyViewModel
import foliocontrol.android.foliocontrolandroid.ui.viewModels.common.ErrorScreen
import foliocontrol.android.foliocontrolandroid.ui.viewModels.common.UiState

val tabItems = listOf(
    TabItem("Details", Icons.Outlined.Home, Icons.Filled.Home),
    TabItem("Premises", Icons.Outlined.List, Icons.Filled.List),
    TabItem("Photos", Icons.Outlined.Phone, Icons.Filled.Phone),
    TabItem("Document", Icons.Outlined.Build, Icons.Filled.Build),
)


data class TabItem(
    val title: String, val unselectedIcon: ImageVector, val selectedIcon: ImageVector
)


@Composable
fun PropertyOverviewScreen(propertyViewModel: PropertyViewModel, navigateTo: (Any?) -> Unit = {}) {
    when (propertyViewModel.uiState) {
        is UiState.LoggedOut -> {
            navigateTo("home")
        }

        is UiState.Success -> {
            Overview(propertyViewModel, navigateTo)
        }

        is UiState.Loading -> {
            LoadingScreen()
        }

        else -> {
            ErrorScreen(errorMessage = (propertyViewModel.uiState as UiState.Error).message,
                onRetry = { propertyViewModel.getData() })
        }
    }
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Overview(
    propertyViewModel: PropertyViewModel, navigateTo: (Any?) -> Unit = {}

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
                    (if (selectedTabIndex == index) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.secondary)?.let {
                        Icon(
                            tint = it,
                            imageVector = if (selectedTabIndex == index) item.selectedIcon else item.unselectedIcon,
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
                if (index == 0) PropertyDetailScreen(propertyViewModel, navigateTo)
                else if (index == 1) PropertyPremisesScreen()
                else if (index == 2) PropertyPhotosScreen()
                else PropertyDocumentsScreen()
            }

        }


    }

}





