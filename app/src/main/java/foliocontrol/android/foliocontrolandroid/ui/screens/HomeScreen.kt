package foliocontrol.android.foliocontrolandroid.screens

import PropertyCard
import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import foliocontrol.android.foliocontrolandroid.ui.components.dialogs.AddPropertyDialog
import foliocontrol.android.foliocontrolandroid.ui.viewModels.PropertyViewModel
import foliocontrol.android.foliocontrolandroid.ui.viewModels.common.ErrorScreen
import foliocontrol.android.foliocontrolandroid.ui.viewModels.common.LoadingScreen
import foliocontrol.android.foliocontrolandroid.ui.viewModels.common.UiState
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(propertyViewModel: PropertyViewModel, navigateTo: (Any?) -> Unit = {}) {
    DisposableEffect(propertyViewModel.partnershipListState) {
        if (propertyViewModel.propertyListState.isEmpty()) {
            propertyViewModel.getData()
        }
        Log.i("TEST", "Home: ${propertyViewModel.uiState}")
        onDispose { }
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
            LoadingScreen()
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
    propertyViewModel: PropertyViewModel, navigateTo: (Any?) -> Unit = {}, offline: Boolean = false
) {
    val openAddPropertyDialog = remember { mutableStateOf(false) }
    val isLoading = propertyViewModel.uiState == UiState.Loading
    val swipeRefreshState = rememberSwipeRefreshState(isRefreshing = isLoading)
    when {
        openAddPropertyDialog.value -> {
            AddPropertyDialog(onDismissRequest = { openAddPropertyDialog.value = false },
                onConfirmation = {
                    propertyViewModel.handlePropertyAdd()
                    openAddPropertyDialog.value = false
                },
                propertyViewModel = propertyViewModel,
                offline = offline
            )
        }
    }

    SwipeRefresh(state = swipeRefreshState, onRefresh = { propertyViewModel.getData() }) {
        val scope = rememberCoroutineScope()
        val snackbarHostState = remember { SnackbarHostState() }

        scope.launch {
            if (propertyViewModel.uiState is UiState.Offline) {
                Log.i("TEST", "Home: ${propertyViewModel.uiState} -> Got through")
                var result = snackbarHostState.showSnackbar(
                    message = (propertyViewModel.uiState as UiState.Offline).message,
                    actionLabel = "Retry",
                    duration = SnackbarDuration.Indefinite,
                )
                if (result == SnackbarResult.ActionPerformed) {
                    propertyViewModel.getData()
                }

            }
        }



        Scaffold(

            floatingActionButton = {
                if (!offline) {
                    FloatingActionButton(onClick = { openAddPropertyDialog.value = true }) {
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.secondary
                        )
                    }
                }
            },
            snackbarHost = {
                SnackbarHost(hostState = snackbarHostState)
            },
        ) { values ->

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
}
