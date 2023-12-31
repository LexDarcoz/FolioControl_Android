package foliocontrol.android.foliocontrolandroid.ui.screens.portfolio

import PropertyDetailScreen
import android.annotation.SuppressLint
import android.content.Context
import android.net.Uri
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FileCopy
import androidx.compose.material.icons.filled.HomeWork
import androidx.compose.material.icons.filled.PictureInPicture
import androidx.compose.material.icons.filled.Room
import androidx.compose.material.icons.outlined.FileCopy
import androidx.compose.material.icons.outlined.HomeWork
import androidx.compose.material.icons.outlined.PictureInPicture
import androidx.compose.material.icons.outlined.Room
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
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import foliocontrol.android.foliocontrolandroid.domain.Premise
import foliocontrol.android.foliocontrolandroid.domain.Property
import foliocontrol.android.foliocontrolandroid.domain.PropertyDocument
import foliocontrol.android.foliocontrolandroid.ui.viewModels.common.DialogLoader
import foliocontrol.android.foliocontrolandroid.ui.viewModels.common.ErrorScreen
import foliocontrol.android.foliocontrolandroid.ui.viewModels.common.UiState
import foliocontrol.android.foliocontrolandroid.ui.viewModels.common.rememberWindowInfo

val tabItemsList =
    listOf(
        TabItem("Details", Icons.Outlined.HomeWork, Icons.Filled.HomeWork),
        TabItem("Photos", Icons.Outlined.PictureInPicture, Icons.Filled.PictureInPicture),
        TabItem("Document", Icons.Outlined.FileCopy, Icons.Filled.FileCopy),
        TabItem("Premises", Icons.Outlined.Room, Icons.Filled.Room),
    )

data class TabItem(
    val title: String,
    val unselectedIcon: ImageVector,
    val selectedIcon: ImageVector,
)

/**
 * Composable function representing the screen for displaying an overview of a property.
 *
 * @param uiState The UI state representing the current state of the screen (e.g., loading, success, offline).
 * @param propertyState The state of the property being displayed.
 * @param handlePropertyEditName Callback to handle editing the name of the property.
 * @param handlePropertyEditType Callback to handle editing the type of the property.
 * @param handlePropertyEditStreet Callback to handle editing the street of the property.
 * @param handlePropertyEditStreetNumber Callback to handle editing the street number of the property.
 * @param handlePropertyEditZipCode Callback to handle editing the zip code of the property.
 * @param handlePropertyEditCity Callback to handle editing the city of the property.
 * @param handlePropertyEditCountry Callback to handle editing the country of the property.
 * @param clearImage Callback to clear the property image.
 * @param uploadImage Callback to upload an image for the property.
 * @param propertyDocuments List of property documents associated with the property.
 * @param handleDocumentDelete Callback to handle the deletion of a property document.
 * @param downloadFile Callback to download a file associated with a property document.
 * @param handleAddDocumentDateEdit Callback to handle editing the date of a new property document.
 * @param handleAddDocumentTypeEdit Callback to handle editing the type of a new property document.
 * @param propertyDocumentState The state representing a new property document being added.
 * @param uploadDocument Callback to upload a new property document.
 * @param propertyPremises List of premises associated with the property.
 * @param getDataForActiveProperty Callback to fetch data for the active property.
 * @param saveDataForActiveProperty Callback to save data for the active property.
 * @param navigateTo Callback to navigate to a different screen or destination.
 */
@Composable
fun PropertyOverviewScreen(
    // UISTATE
    uiState: UiState,
    // PROPERTY STATE
    propertyState: Property,
    // Edit Detail Data
    handlePropertyEditName: (String) -> Unit = {},
    handlePropertyEditType: (String) -> Unit = {},
    handlePropertyEditStreet: (String) -> Unit = {},
    handlePropertyEditStreetNumber: (String) -> Unit = {},
    handlePropertyEditZipCode: (String) -> Unit = {},
    handlePropertyEditCity: (String) -> Unit = {},
    handlePropertyEditCountry: (String) -> Unit = {},
    // Image Functions
    clearImage: () -> Unit,
    uploadImage: (Context, Uri?) -> Unit,
    // Document Functions
    propertyDocuments: List<PropertyDocument>,
    handleDocumentDelete: (Int) -> Unit,
    downloadFile: (String) -> Long,
    handleAddDocumentDateEdit: (String) -> Unit,
    handleAddDocumentTypeEdit: (String) -> Unit,
    propertyDocumentState: PropertyDocument,
    uploadDocument: (Context, Uri) -> Unit,
    // Premises
    propertyPremises: List<Premise>,
    // GETDATA
    getDataForActiveProperty: () -> Unit,
    // SAVE DATA
    saveDataForActiveProperty: () -> Unit,
    navigateTo: (Any?) -> Unit,
) {
    DisposableEffect(propertyState.propertyID) {
        getDataForActiveProperty()

        onDispose {}
    }

    when (uiState) {
        is UiState.LoggedOut -> {
            navigateTo("home")
        }

        is UiState.Success -> {
            Overview(
                uiState = uiState,
                propertyState = propertyState,
                handlePropertyEditName = handlePropertyEditName,
                handlePropertyEditType = handlePropertyEditType,
                handlePropertyEditStreet = handlePropertyEditStreet,
                handlePropertyEditStreetNumber = handlePropertyEditStreetNumber,
                handlePropertyEditZipCode = handlePropertyEditZipCode,
                handlePropertyEditCity = handlePropertyEditCity,
                handlePropertyEditCountry = handlePropertyEditCountry,
                clearImage = clearImage,
                uploadImage = uploadImage,
                propertyDocuments = propertyDocuments,
                handleDocumentDelete = handleDocumentDelete,
                downloadFile = downloadFile,
                handleAddDocumentDateEdit = handleAddDocumentDateEdit,
                handleAddDocumentTypeEdit = handleAddDocumentTypeEdit,
                propertyDocumentState = propertyDocumentState,
                uploadDocument = uploadDocument,
                propertyPremises = propertyPremises,
                saveDataForActiveProperty = saveDataForActiveProperty,
                getDataForActiveProperty = getDataForActiveProperty,
                navigateTo = navigateTo,
            )
        }

        is UiState.Offline -> {
            Overview(
                uiState = uiState,
                propertyState = propertyState,
                handlePropertyEditName = handlePropertyEditName,
                handlePropertyEditType = handlePropertyEditType,
                handlePropertyEditStreet = handlePropertyEditStreet,
                handlePropertyEditStreetNumber = handlePropertyEditStreetNumber,
                handlePropertyEditZipCode = handlePropertyEditZipCode,
                handlePropertyEditCity = handlePropertyEditCity,
                handlePropertyEditCountry = handlePropertyEditCountry,
                clearImage = clearImage,
                uploadImage = uploadImage,
                propertyDocuments = propertyDocuments,
                handleDocumentDelete = handleDocumentDelete,
                downloadFile = downloadFile,
                handleAddDocumentDateEdit = handleAddDocumentDateEdit,
                handleAddDocumentTypeEdit = handleAddDocumentTypeEdit,
                propertyDocumentState = propertyDocumentState,
                uploadDocument = uploadDocument,
                propertyPremises = propertyPremises,
                saveDataForActiveProperty = saveDataForActiveProperty,
                getDataForActiveProperty = getDataForActiveProperty,
                navigateTo = navigateTo,
                offline = true,
            )
        }

        is UiState.Loading -> {
        }

        else -> {
            ErrorScreen(
                errorMessage = (uiState as UiState.Error).message,
                onRetry = { getDataForActiveProperty() },
            )
        }
    }
}

/**
 * Composable function representing the overview of a property, including details, photos, documents, and premises.
 *
 * @param uiState The UI state representing the current state of the screen (e.g., loading, success, offline).
 * @param propertyState The state of the property being displayed.
 * @param handlePropertyEditName Callback to handle editing the name of the property.
 * @param handlePropertyEditType Callback to handle editing the type of the property.
 * @param handlePropertyEditStreet Callback to handle editing the street of the property.
 * @param handlePropertyEditStreetNumber Callback to handle editing the street number of the property.
 * @param handlePropertyEditZipCode Callback to handle editing the zip code of the property.
 * @param handlePropertyEditCity Callback to handle editing the city of the property.
 * @param handlePropertyEditCountry Callback to handle editing the country of the property.
 * @param clearImage Callback to clear the property image.
 * @param uploadImage Callback to upload an image for the property.
 * @param propertyDocuments List of property documents associated with the property.
 * @param handleDocumentDelete Callback to handle the deletion of a property document.
 * @param downloadFile Callback to download a file associated with a property document.
 * @param handleAddDocumentDateEdit Callback to handle editing the date of a new property document.
 * @param handleAddDocumentTypeEdit Callback to handle editing the type of a new property document.
 * @param propertyDocumentState The state representing a new property document being added.
 * @param uploadDocument Callback to upload a new property document.
 * @param propertyPremises List of premises associated with the property.
 * @param getDataForActiveProperty Callback to fetch data for the active property.
 * @param saveDataForActiveProperty Callback to save data for the active property.
 * @param navigateTo Callback to navigate to a different screen or destination.
 * @param offline Boolean flag indicating whether the app is in offline mode.
 */
@SuppressLint("CoroutineCreationDuringComposition")
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Overview(
    // UISTATE
    uiState: UiState,
    // PROPERTY STATE
    propertyState: Property,
    // Edit Detail Data
    handlePropertyEditName: (String) -> Unit = {},
    handlePropertyEditType: (String) -> Unit = {},
    handlePropertyEditStreet: (String) -> Unit = {},
    handlePropertyEditStreetNumber: (String) -> Unit = {},
    handlePropertyEditZipCode: (String) -> Unit = {},
    handlePropertyEditCity: (String) -> Unit = {},
    handlePropertyEditCountry: (String) -> Unit = {},
    // Image Functions
    clearImage: () -> Unit,
    uploadImage: (Context, Uri?) -> Unit,
    // Document Functions
    propertyDocuments: List<PropertyDocument>,
    handleDocumentDelete: (Int) -> Unit,
    downloadFile: (String) -> Long,
    handleAddDocumentDateEdit: (String) -> Unit,
    handleAddDocumentTypeEdit: (String) -> Unit,
    propertyDocumentState: PropertyDocument,
    uploadDocument: (Context, Uri) -> Unit,
    // Premises
    propertyPremises: List<Premise>,
    // GETDATA
    getDataForActiveProperty: () -> Unit,
    // SAVE DATA
    saveDataForActiveProperty: () -> Unit,
    navigateTo: (Any?) -> Unit,
    offline: Boolean = false,
) {
    var loading by remember {
        mutableStateOf(false)
    }
    var tabItems = tabItemsList
    if (propertyState.propertyType != "Apartment") {
        tabItems = tabItems.subList(0, tabItems.size - 1)
    }
    when (uiState) {
        is UiState.Loading -> {
            loading = false
        }

        else -> {
            loading = false
        }
    }

    val swipeRefreshState = rememberSwipeRefreshState(isRefreshing = loading)
    val pagerState =
        rememberPagerState {
            tabItems.size
        }
    var selectedTabIndex by remember {
        mutableStateOf(0)
    }

    val windowInfo = rememberWindowInfo()

    LaunchedEffect(selectedTabIndex) {
        pagerState.animateScrollToPage(selectedTabIndex)
    }
    LaunchedEffect(pagerState.currentPage, pagerState.isScrollInProgress) {
        if (!pagerState.isScrollInProgress) {
            selectedTabIndex = pagerState.currentPage
        }
    }
    when {
        loading -> {
            // Loading
            DialogLoader()
        }
    }
    SwipeRefresh(state = swipeRefreshState, onRefresh = {
        getDataForActiveProperty()
    }) {
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
                        @Suppress("ktlint:standard:max-line-length")
                        (if (selectedTabIndex == index) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSecondary).let {
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
                    .fillMaxSize()
                    .zIndex(1f),
            ) { index ->
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    if (index == 0) {
                        PropertyDetailScreen(
                            saveDataForActiveProperty,
                            propertyState,
                            handlePropertyEditName,
                            handlePropertyEditType,
                            handlePropertyEditStreet,
                            handlePropertyEditStreetNumber,
                            handlePropertyEditZipCode,
                            handlePropertyEditCity,
                            handlePropertyEditCountry,
                            navigateTo = navigateTo,
                            offline,
                        )
                    } else if (index == 1) {
                        PropertyPhotosScreen(
                            getDataForActiveProperty,
                            clearImage,
                            uploadImage,
                            propertyState,
                            uiState,
                            windowInfo,
                            offline,
                        )
                    } else if (index == 2) {
                        PropertyDocumentsScreen(
                            uiState,
                            propertyDocuments,
                            getDataForActiveProperty,
                            handleDocumentDelete,
                            downloadFile,
                            handleAddDocumentDateEdit,
                            handleAddDocumentTypeEdit,
                            propertyDocumentState,
                            uploadDocument,
                            windowInfo,
                            offline,
                        )
                    } else {
                        PremisesListScreen(propertyPremises, windowInfo, offline)
                    }
                }
            }
        }
    }
}
