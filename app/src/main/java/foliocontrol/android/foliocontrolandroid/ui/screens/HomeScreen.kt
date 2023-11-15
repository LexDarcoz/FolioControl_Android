package foliocontrol.android.foliocontrolandroid.screens

import PropertyCard
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import foliocontrol.android.foliocontrolandroid.ui.components.dialogs.AddPropertyDialog
import foliocontrol.android.foliocontrolandroid.ui.viewModels.PropertyViewModel
import foliocontrol.android.foliocontrolandroid.ui.viewModels.common.ErrorScreen
import foliocontrol.android.foliocontrolandroid.ui.viewModels.common.LoadingScreen
import foliocontrol.android.foliocontrolandroid.ui.viewModels.common.UiState

@Composable
fun HomeScreen(propertyViewModel: PropertyViewModel, navigateTo: (Any?) -> Unit = {}) {
    DisposableEffect(propertyViewModel.partnershipList) {
        propertyViewModel.getData()
        onDispose { }
    }

    when (propertyViewModel.uiState) {
        is UiState.LoggedOut -> {
            navigateTo("home")
        }

        is UiState.Success -> {
            Home(propertyViewModel, navigateTo)
        }

        is UiState.Loading -> {
            LoadingScreen()
        }

        else -> {
            ErrorScreen(
                errorMessage = (propertyViewModel.uiState as UiState.Error).message,
                onRetry = { propertyViewModel.getData() }
            )
        }
    }
}

@Composable
fun Home(
    propertyViewModel: PropertyViewModel,
    navigateTo: (Any?) -> Unit = {}
) {
    val openAddPropertyDialog = remember { mutableStateOf(false) }
    when {
        openAddPropertyDialog.value -> {
            AddPropertyDialog(
                onDismissRequest = { openAddPropertyDialog.value = false },
                onConfirmation = {
                    propertyViewModel.handlePropertyAdd()
                    openAddPropertyDialog.value = false
                },
                propertyViewModel = propertyViewModel

            )
        }
    }

    Scaffold(floatingActionButton = {
        FloatingActionButton(onClick = { openAddPropertyDialog.value = true }) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.secondary
            )
        }
    }) { values ->

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
