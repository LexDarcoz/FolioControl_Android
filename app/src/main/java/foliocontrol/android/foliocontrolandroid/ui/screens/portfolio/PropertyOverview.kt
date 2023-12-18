package foliocontrol.android.foliocontrolandroid.ui.screens.portfolio

import PropertyDetailScreen
import android.annotation.SuppressLint
import android.util.Log
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
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.zIndex
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import foliocontrol.android.foliocontrolandroid.ui.viewModels.common.LoadingScreen

import foliocontrol.android.foliocontrolandroid.ui.viewModels.PropertyViewModel
import foliocontrol.android.foliocontrolandroid.ui.viewModels.common.ErrorScreen
import foliocontrol.android.foliocontrolandroid.ui.viewModels.common.UiState
import kotlinx.coroutines.launch

val tabItemsList = listOf(
    TabItem("Details", Icons.Outlined.Home, Icons.Filled.Home),
    TabItem("Photos", Icons.Outlined.Phone, Icons.Filled.Phone),
    TabItem("Document", Icons.Outlined.Build, Icons.Filled.Build),
    TabItem("Premises", Icons.Outlined.List, Icons.Filled.List),
)


data class TabItem(
    val title: String, val unselectedIcon: ImageVector, val selectedIcon: ImageVector
)

@Composable
fun PropertyOverviewScreen(propertyViewModel: PropertyViewModel, navigateTo: (Any?) -> Unit = {}) {

    DisposableEffect(propertyViewModel.propertyState) {
        if (propertyViewModel.propertyPremises.isEmpty()) {
            propertyViewModel.getDataForActiveProperty()
            Log.i("TEST", "PropertyPremisesScreen: ")
        }
        onDispose {}
    }


    when (propertyViewModel.uiState) {
        is UiState.LoggedOut -> {
            navigateTo("home")
        }

        is UiState.Success -> {
            Overview(propertyViewModel, navigateTo)
        }

        is UiState.Offline -> {
            Overview(propertyViewModel, navigateTo, offline = true)
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


@SuppressLint("CoroutineCreationDuringComposition")
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Overview(
    propertyViewModel: PropertyViewModel, navigateTo: (Any?) -> Unit = {}, offline: Boolean = false

) {
    var tabItems = tabItemsList
    if (propertyViewModel.propertyState.propertyType != "Apartment") {
        tabItems = tabItems.subList(0, tabItems.size - 1)
    }


    val isLoading = propertyViewModel.uiState == UiState.Loading
    val swipeRefreshState = rememberSwipeRefreshState(isRefreshing = isLoading)
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

    SwipeRefresh(state = swipeRefreshState, onRefresh = { propertyViewModel.getData() }) {

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


                    if (index == 0) PropertyDetailScreen(propertyViewModel, navigateTo, offline)
                    else if (index == 1) PropertyPhotosScreen(propertyViewModel, offline)
                    else if (index == 2) PropertyDocumentsScreen(
                        propertyViewModel, offline
                    ) else PropertyPremisesScreen(propertyViewModel, offline)
                }

            }


        }
    }
}





