package foliocontrol.android.foliocontrolandroid.ui.screens.portfolio

import PropertyDetailScreen
import android.annotation.SuppressLint
import android.content.Context
import android.net.Uri
import androidx.compose.animation.core.tween
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

val tabItemsList = listOf(
    TabItem("Details", Icons.Outlined.HomeWork, Icons.Filled.HomeWork),
    TabItem("Photos", Icons.Outlined.PictureInPicture, Icons.Filled.PictureInPicture),
    TabItem("Document", Icons.Outlined.FileCopy, Icons.Filled.FileCopy),
    TabItem("Premises", Icons.Outlined.Room, Icons.Filled.Room)
)

data class TabItem(
    val title: String, val unselectedIcon: ImageVector, val selectedIcon: ImageVector
)

@Composable
fun PropertyOverviewScreen(
    //UISTATE
    uiState: UiState,
    //PROPERTY STATE
    propertyState: Property,
    //Edit Detail Data
    handlePropertyEditName: (String) -> Unit = {},
    handlePropertyEditType: (String) -> Unit = {},
    handlePropertyEditStreet: (String) -> Unit = {},
    handlePropertyEditStreetNumber: (String) -> Unit = {},
    handlePropertyEditZipCode: (String) -> Unit = {},
    handlePropertyEditCity: (String) -> Unit = {},
    handlePropertyEditCountry: (String) -> Unit = {},
    //Image Functions
    clearImage: () -> Unit, uploadImage: (Context, Uri?) -> Unit,
    //Document Functions
    propertyDocuments: List<PropertyDocument>,
    handleDocumentDelete: (Int) -> Unit,
    downloadFile: (String) -> Long,
    handleAddDocumentDateEdit: (String) -> Unit,
    handleAddDocumentTypeEdit: (String) -> Unit,
    propertyDocumentState: PropertyDocument,
    uploadDocument: (Context, Uri) -> Unit,
    //Premises
    propertyPremises: List<Premise>,
    //GETDATA
    getDataForActiveProperty: () -> Unit,
    //SAVE DATA
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
                offline = true
            )
        }

        is UiState.Loading -> {

        }


        else -> {
            ErrorScreen(errorMessage = (uiState as UiState.Error).message,
                onRetry = { getDataForActiveProperty() })
        }
    }
}

@SuppressLint("CoroutineCreationDuringComposition")
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Overview(
    //UISTATE
    uiState: UiState,
    //PROPERTY STATE
    propertyState: Property,
    //Edit Detail Data
    handlePropertyEditName: (String) -> Unit = {},
    handlePropertyEditType: (String) -> Unit = {},
    handlePropertyEditStreet: (String) -> Unit = {},
    handlePropertyEditStreetNumber: (String) -> Unit = {},
    handlePropertyEditZipCode: (String) -> Unit = {},
    handlePropertyEditCity: (String) -> Unit = {},
    handlePropertyEditCountry: (String) -> Unit = {},
    //Image Functions
    clearImage: () -> Unit, uploadImage: (Context, Uri?) -> Unit,
    //Document Functions
    propertyDocuments: List<PropertyDocument>,
    handleDocumentDelete: (Int) -> Unit,
    downloadFile: (String) -> Long,
    handleAddDocumentDateEdit: (String) -> Unit,
    handleAddDocumentTypeEdit: (String) -> Unit,
    propertyDocumentState: PropertyDocument,
    uploadDocument: (Context, Uri) -> Unit,
    //Premises
    propertyPremises: List<Premise>,
    //GETDATA
    getDataForActiveProperty: () -> Unit,
    //SAVE DATA
    saveDataForActiveProperty: () -> Unit,

    navigateTo: (Any?) -> Unit,
    offline: Boolean = false,
) {
    var loading by remember { mutableStateOf(true) }
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
    val pagerState = rememberPagerState {
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
                containerColor = MaterialTheme.colorScheme.onPrimary
            ) {
                tabItems.forEachIndexed { index, item ->
                    Tab(selected = selectedTabIndex == index, onClick = {
                        selectedTabIndex = index
                    }, text = {
                        Text(text = item.title, color = MaterialTheme.colorScheme.primary)
                    }, icon = {
                        (if (selectedTabIndex == index) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSecondary)?.let { // ktlint-disable max-line-length
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
                    .fillMaxSize()
                    .zIndex(1f)
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
                            offline
                        )
                    } else if (index == 1) {
                        PropertyPhotosScreen(
                            getDataForActiveProperty,
                            clearImage,
                            uploadImage,
                            propertyState,
                            uiState,
                            windowInfo,
                            offline
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
                            offline
                        )
                    } else {
                        PremisesListScreen(propertyPremises, windowInfo, offline)
                    }
                }
            }
        }
    }
}
