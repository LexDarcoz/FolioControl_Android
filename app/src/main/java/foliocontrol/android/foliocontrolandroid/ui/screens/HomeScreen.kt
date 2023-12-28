package foliocontrol.android.foliocontrolandroid.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DomainAdd
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.vector.ImageVector
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import foliocontrol.android.foliocontrolandroid.ui.components.InfoDialog
import foliocontrol.android.foliocontrolandroid.ui.components.card.PropertyCard
import foliocontrol.android.foliocontrolandroid.ui.components.dialogs.AddPropertyDialog
import foliocontrol.android.foliocontrolandroid.ui.components.foliocomponents.MultiFloatingButton
import foliocontrol.android.foliocontrolandroid.ui.components.foliocomponents.SearchBar
import foliocontrol.android.foliocontrolandroid.ui.screens.loadingSkeleton.HomeSkeleton
import foliocontrol.android.foliocontrolandroid.ui.viewModels.PropertyViewModel
import foliocontrol.android.foliocontrolandroid.ui.viewModels.common.DialogLoader
import foliocontrol.android.foliocontrolandroid.ui.viewModels.common.ErrorScreen
import foliocontrol.android.foliocontrolandroid.ui.viewModels.common.LoadingScreen
import foliocontrol.android.foliocontrolandroid.ui.viewModels.common.UiState
import kotlinx.coroutines.launch

enum class MultiFloatingState {
    Expanded, Collapsed
}

class MinFabItem(
    val icon: ImageVector, val label: String, val identifier: String
)

enum class Identifier {
    SearchFab, AddPropertyFab
}

@Composable
fun HomeScreen(propertyViewModel: PropertyViewModel, navigateTo: (Any?) -> Unit) {
    DisposableEffect(propertyViewModel.currentPartnership) {
        propertyViewModel.getData()
        onDispose {}
    }

    when (propertyViewModel.uiState) {
        is UiState.LoggedOut -> {
            navigateTo("home")
        }

        is UiState.Success -> {
            Home(propertyViewModel, navigateTo)
        }

        is UiState.Offline -> {
            Home(propertyViewModel, navigateTo, offline = true)
        }

        is UiState.Loading -> {
            HomeSkeleton()
        }

        else -> {
            ErrorScreen(errorMessage = (propertyViewModel.uiState as UiState.Error).message,
                onRetry = { propertyViewModel.getData() })
        }
    }
}

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun Home(
    propertyViewModel: PropertyViewModel,
    navigateTo: (Any?) -> Unit,
    offline: Boolean = false,
    loading: Boolean = false
) {
    var multiFloatingState by remember {
        mutableStateOf(MultiFloatingState.Collapsed)
    }
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }
    val swipeRefreshState = rememberSwipeRefreshState(isRefreshing = loading)
    val infoDialog = remember { mutableStateOf(offline) }

    val items = listOf(
        MinFabItem(
            icon = Icons.Filled.Search, label = "Search", identifier = Identifier.SearchFab.name
        ),

        MinFabItem(
            icon = Icons.Filled.DomainAdd,
            label = "Add Property",
            identifier = Identifier.AddPropertyFab.name

        )

    )

    when {
        propertyViewModel.isAddPropertyDialogOpen -> {
            AddPropertyDialog(onDismissRequest = { propertyViewModel.togglePropertyAddDialog() },
                onConfirmation = {
                    propertyViewModel.handlePropertyAdd()
                    propertyViewModel.togglePropertyAddDialog()
                },
                propertyViewModel = propertyViewModel,
                offline = offline
            )
        }
    }
    SwipeRefresh(state = swipeRefreshState, onRefresh = { propertyViewModel.getData() }) {
        scope.launch {
            if (propertyViewModel.uiState is UiState.Offline) {
                var result = snackbarHostState.showSnackbar(
                    message = (propertyViewModel.uiState as UiState.Offline).message,
                    actionLabel = "Retry",
                    duration = SnackbarDuration.Indefinite
                )
                if (result == SnackbarResult.ActionPerformed) {
                    propertyViewModel.getData()
                }
            }
        }

        Scaffold(floatingActionButton = {
            if (!offline) {
                MultiFloatingButton(multiFloatingState = multiFloatingState,
                    onMultiFabStateChange = {
                        multiFloatingState = it
                    },
                    items = items,
                    toggleAddPropertyDialog = {
                        propertyViewModel.togglePropertyAddDialog()
                    },
                    toggleSearchBar = {
                        propertyViewModel.toggleSearchBar()
                    })
            }
        }, snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        }) { values ->
            Column {
                if (propertyViewModel.isSearchBarEnabled) {
                    SearchBar(propertyViewModel)
                    LazyColumn(contentPadding = values) {
                        items(propertyViewModel.filteredList) { property ->
                            PropertyCard(
                                propertyViewModel = propertyViewModel,
                                property = property,
                                navigateTo = navigateTo

                            )
                        }
                    }
                } else {
                    LazyColumn(contentPadding = values) {
                        items(propertyViewModel.propertyListState) { property ->
                            PropertyCard(
                                propertyViewModel = propertyViewModel,
                                property = property,
                                navigateTo = navigateTo

                            )
                        }
                    }
                }
            }

            if (infoDialog.value) {
                InfoDialog(title = "Whoops!",
                    desc = "No Internet Connection found.\n" + "Check your connection or try again.",
                    onDismiss = {
                        infoDialog.value = false
                    })
            }
        }
    }
}
