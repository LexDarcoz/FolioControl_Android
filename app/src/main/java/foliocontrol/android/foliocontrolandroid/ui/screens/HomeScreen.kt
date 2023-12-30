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
import foliocontrol.android.foliocontrolandroid.domain.Partnership
import foliocontrol.android.foliocontrolandroid.domain.Property
import foliocontrol.android.foliocontrolandroid.ui.components.InfoDialog
import foliocontrol.android.foliocontrolandroid.ui.components.card.PropertyCard
import foliocontrol.android.foliocontrolandroid.ui.components.dialogs.AddPropertyDialog
import foliocontrol.android.foliocontrolandroid.ui.components.foliocomponents.MultiFloatingButton
import foliocontrol.android.foliocontrolandroid.ui.components.foliocomponents.SearchBar
import foliocontrol.android.foliocontrolandroid.ui.screens.loadingSkeleton.HomeSkeleton
import foliocontrol.android.foliocontrolandroid.ui.viewModels.common.ErrorScreen
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
fun HomeScreen(
    getData: () -> Unit = { },
    toggleSearchBar: () -> Unit = { },
    isSearchBarEnabled: Boolean = false,
    filteredList: List<Property>,
    filterProperties: (String) -> Unit,
    propertyListState: List<Property>,
    uiState: UiState,
    addPropertyState: Property,
    handlePropertyNameEdit: (String) -> Unit,
    handlePropertyTypeEdit: (String) -> Unit,
    handlePropertyStreetAddEdit: (String) -> Unit,
    handlePropertyStreetNumberAddEdit: (String) -> Unit,
    handlePropertyZipCodeAddEdit: (String) -> Unit,
    handlePropertyCityAddEdit: (String) -> Unit,
    handlePropertyCountryAddEdit: (String) -> Unit,
    selectProperty: (Property) -> Unit,
    deleteProperty: (Int) -> Unit,
    togglePropertyAddDialog: () -> Unit, handlePropertyAdd: () -> Unit = {},
    currentPartnership: Partnership,
    isAddPropertyDialogOpen: Boolean,
    navigateTo: (Any?) -> Unit,
) {
    DisposableEffect(currentPartnership) {
        getData()
        onDispose {}
    }

    when (uiState) {
        is UiState.LoggedOut -> {
            navigateTo("home")
        }

        is UiState.Success -> {
            Home(
                getData = { getData() },
                toggleSearchBar = { toggleSearchBar() },
                isSearchBarEnabled = isSearchBarEnabled,
                filteredList = filteredList,
                filterProperties = { filterProperties(it) },
                propertyListState = propertyListState,
                uiState = uiState,
                addPropertyState = addPropertyState,
                handlePropertyNameEdit = { handlePropertyNameEdit(it) },
                handlePropertyTypeEdit = { handlePropertyTypeEdit(it) },
                handlePropertyStreetAddEdit = { handlePropertyStreetAddEdit(it) },
                handlePropertyStreetNumberAddEdit = {
                    handlePropertyStreetNumberAddEdit(
                        it
                    )
                },
                handlePropertyZipCodeAddEdit = { handlePropertyZipCodeAddEdit(it) },
                handlePropertyCityAddEdit = { handlePropertyCityAddEdit(it) },
                handlePropertyCountryAddEdit = { handlePropertyCountryAddEdit(it) },
                selectProperty = { selectProperty(it) },
                deleteProperty = { deleteProperty(it) },
                togglePropertyAddDialog = { togglePropertyAddDialog() },
                handlePropertyAdd = { handlePropertyAdd() },
                isAddPropertyDialogOpen = isAddPropertyDialogOpen,
                navigateTo = navigateTo
            )
        }

        is UiState.Offline -> {
            Home(
                getData = { getData() },
                toggleSearchBar = { toggleSearchBar() },
                isSearchBarEnabled = isSearchBarEnabled,
                filteredList = filteredList,
                filterProperties = { filterProperties(it) },
                propertyListState = propertyListState,
                uiState = uiState,
                addPropertyState = addPropertyState,
                handlePropertyNameEdit = { handlePropertyNameEdit(it) },
                handlePropertyTypeEdit = { handlePropertyTypeEdit(it) },
                handlePropertyStreetAddEdit = { handlePropertyStreetAddEdit(it) },
                handlePropertyStreetNumberAddEdit = {
                    handlePropertyStreetNumberAddEdit(
                        it
                    )
                },
                handlePropertyZipCodeAddEdit = { handlePropertyZipCodeAddEdit(it) },
                handlePropertyCityAddEdit = { handlePropertyCityAddEdit(it) },
                handlePropertyCountryAddEdit = { handlePropertyCountryAddEdit(it) },
                selectProperty = { selectProperty(it) },
                deleteProperty = { deleteProperty(it) },
                togglePropertyAddDialog = { togglePropertyAddDialog() },
                handlePropertyAdd = { handlePropertyAdd() },
                isAddPropertyDialogOpen = isAddPropertyDialogOpen,
                navigateTo = navigateTo,
                offline = true
            )
        }

        is UiState.Loading -> {
            HomeSkeleton()
        }

        else -> {
            ErrorScreen(errorMessage = (uiState as UiState.Error).message, onRetry = { getData() })
        }
    }
}

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun Home(
    getData: () -> Unit = { },
    toggleSearchBar: () -> Unit = { },
    isSearchBarEnabled: Boolean = false,
    filteredList: List<Property>,
    filterProperties: (String) -> Unit,
    propertyListState: List<Property>,
    uiState: UiState,
    addPropertyState: Property,
    handlePropertyNameEdit: (String) -> Unit,
    handlePropertyTypeEdit: (String) -> Unit,
    handlePropertyStreetAddEdit: (String) -> Unit,
    handlePropertyStreetNumberAddEdit: (String) -> Unit,
    handlePropertyZipCodeAddEdit: (String) -> Unit,
    handlePropertyCityAddEdit: (String) -> Unit,
    handlePropertyCountryAddEdit: (String) -> Unit,
    selectProperty: (Property) -> Unit,
    deleteProperty: (Int) -> Unit,
    togglePropertyAddDialog: () -> Unit, handlePropertyAdd: () -> Unit = {},
    isAddPropertyDialogOpen: Boolean, offline: Boolean = false, loading: Boolean = false,
    navigateTo: (Any?) -> Unit,
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
        isAddPropertyDialogOpen -> {
            AddPropertyDialog(
                onDismissRequest = { togglePropertyAddDialog() },
                onConfirmation = {
                    handlePropertyAdd()
                    togglePropertyAddDialog()
                },
                getData = { getData() },
                addPropertyState = addPropertyState,
                handlePropertyNameAddEdit = handlePropertyNameEdit,
                handlePropertyTypeAddEdit = handlePropertyTypeEdit,
                handlePropertyStreetAddEdit = handlePropertyStreetAddEdit,
                handlePropertyStreetNumberAddEdit = handlePropertyStreetNumberAddEdit,
                handlePropertyZipCodeAddEdit = handlePropertyZipCodeAddEdit,
                handlePropertyCityAddEdit = handlePropertyCityAddEdit,
                handlePropertyCountryAddEdit = handlePropertyCountryAddEdit,
                offline = offline
            )
        }
    }
    SwipeRefresh(state = swipeRefreshState, onRefresh = { getData() }) {
        scope.launch {
            if (uiState is UiState.Offline) {
                var result = snackbarHostState.showSnackbar(
                    message = uiState.message,
                    actionLabel = "Retry",
                    duration = SnackbarDuration.Indefinite
                )
                if (result == SnackbarResult.ActionPerformed) {
                    getData()
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
                        togglePropertyAddDialog()
                    },
                    toggleSearchBar = {
                        toggleSearchBar()
                    })
            }
        }, snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        }) { values ->
            Column {
                if (isSearchBarEnabled) {
                    SearchBar(toggleSearchBar = toggleSearchBar,
                        filterProperties = { filterProperties(it) })
                    LazyColumn(contentPadding = values) {
                        items(
                            items = filteredList,
                            key = { property -> property.propertyID }) { property ->
                            PropertyCard(
                                property = property,
                                uiState = uiState,
                                handlePropertyDelete = { deleteProperty(it) },

                                getData = { getData() },
                                selectProperty = { selectProperty(it) },
                                navigateTo = navigateTo

                            )
                        }
                    }
                } else {
                    LazyColumn(contentPadding = values) {
                        items(propertyListState) { property ->
                            PropertyCard(
                                property = property,
                                uiState = uiState,
                                handlePropertyDelete = { deleteProperty(it) },
                                getData = { getData() },
                                selectProperty = { selectProperty(it) },
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
