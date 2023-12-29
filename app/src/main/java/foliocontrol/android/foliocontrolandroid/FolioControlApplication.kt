package foliocontrol.android.foliocontrolandroid

import NavigationDrawer
import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import foliocontrol.android.foliocontrolandroid.components.BottomNavigation
import foliocontrol.android.foliocontrolandroid.components.Navbar
import foliocontrol.android.foliocontrolandroid.screens.AccountScreen
import foliocontrol.android.foliocontrolandroid.screens.AuthScreen
import foliocontrol.android.foliocontrolandroid.ui.screens.portfolio.PropertyOverviewScreen
import foliocontrol.android.foliocontrolandroid.ui.viewModels.AccountViewModel
import foliocontrol.android.foliocontrolandroid.ui.viewModels.AuthViewModel
import foliocontrol.android.foliocontrolandroid.ui.viewModels.PropertyViewModel
import foliocontrol.android.foliocontrolandroid.ui.viewModels.common.UiState
import foliocontrol.android.foliocontrolandroid.ui.viewModels.common.WindowInfo
import foliocontrol.android.foliocontrolandroid.ui.viewModels.common.rememberWindowInfo
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FolioControlApplication(
    authViewModel: AuthViewModel = viewModel(factory = AppViewModelProvider.Factory),
    propertyViewModel: PropertyViewModel = viewModel(factory = AppViewModelProvider.Factory),
    accountViewModel: AccountViewModel = viewModel(factory = AppViewModelProvider.Factory),
    navController: NavHostController = rememberNavController()
) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())
    val windowInfo = rememberWindowInfo()
    val scope = rememberCoroutineScope()
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    suspend fun toggleDrawer() {
        when (drawerState.currentValue) {
            DrawerValue.Closed -> drawerState.open()
            DrawerValue.Open -> drawerState.close()
        }
    }
    fun navigateTo(route: String) {
        navController.navigate(route)
    }
    fun navigateUp() {
        navController.navigateUp()
    }

    NavigationDrawer(
        drawerState = drawerState,
        toggleDrawer = {
            scope.launch {
                toggleDrawer()
            }
        },
        navController = navController,
        currentPartnership = propertyViewModel.currentPartnership,
        partnershipList = propertyViewModel.partnershipListState,
        switchPartnership = { propertyViewModel.switchPartnership(it) },
        navigateTo = {
            navigateTo(it)
        },

        ) {
        Scaffold(modifier = Modifier.fillMaxWidth(),
            contentColor = MaterialTheme.colorScheme.primary,
            contentWindowInsets = WindowInsets(
                left = 0.dp, top = 0.dp, right = 0.dp, bottom = 0.dp
            ),

            bottomBar = {
                when (authViewModel.loginUiState) {
                    is UiState.Success -> {
                        if (windowInfo.screenWidthInfo is WindowInfo.WindowType.Compact) {
                            BottomNavigation(
                                currentPartnership = propertyViewModel.currentPartnership,
                                partnershipList = propertyViewModel.partnershipListState,
                                switchPartnership = { propertyViewModel.switchPartnership(it) },
                                navigateTo = {
                                    navigateTo(
                                        it
                                    )
                                },
                            )
                        }
                    }

                    else -> {
                    }
                }
            },


            topBar = {
                Navbar(scrollBehavior,
                    authViewModel.loginUiState,
                    logOut = { authViewModel.logOut() },
                    navigateTo = {
                        navigateTo(
                            it
                        )
                    },
                    navigateUp = {
                        navigateUp()
                    },
                    toggleDrawer = {
                        scope.launch {
                            toggleDrawer()
                        }
                    })
            }

        ) { innerPadding ->
            Box(modifier = Modifier.padding(innerPadding)) {
                AppNavigator(
                    navController = navController,
                    authViewModel = authViewModel,
                    propertyViewModel = propertyViewModel,
                    accountViewModel = accountViewModel,
                )
            }
        }
    }

}

@Composable
fun AppNavigator(
    navController: NavHostController,
    authViewModel: AuthViewModel,
    propertyViewModel: PropertyViewModel,
    accountViewModel: AccountViewModel,
) {
    NavHost(navController = navController, startDestination = "Home") {
        // Auth
        composable("Home") {
            AuthScreen(
                getData = { propertyViewModel.getData() },
                loginUiState = authViewModel.loginUiState,
                toggleSearchBar = { propertyViewModel.toggleSearchBar() },
                isSearchBarEnabled = propertyViewModel.isSearchBarEnabled,
                filteredList = propertyViewModel.filteredList,
                filterProperties = { propertyViewModel.filterProperties(it) },
                propertyListState = propertyViewModel.propertyListState,
                uiState = propertyViewModel.uiState,
                addPropertyState = propertyViewModel.addPropertyState,
                handlePropertyNameEdit = { propertyViewModel.handlePropertyAddEdit(propertyName = it) },
                handlePropertyTypeEdit = { propertyViewModel.handlePropertyAddEdit(propertyType = it) },
                handlePropertyStreetAddEdit = { propertyViewModel.handlePropertyAddEdit(street = it) },
                handlePropertyStreetNumberAddEdit = {
                    propertyViewModel.handlePropertyAddEdit(
                        streetNumber = it
                    )
                },
                handlePropertyZipCodeAddEdit = { propertyViewModel.handlePropertyAddEdit(zipCode = it) },
                handlePropertyCityAddEdit = { propertyViewModel.handlePropertyAddEdit(city = it) },
                handlePropertyCountryAddEdit = { propertyViewModel.handlePropertyAddEdit(country = it) },
                selectProperty = { propertyViewModel.selectProperty(it) },
                deleteProperty = { propertyViewModel.handlePropertyDelete(it) },
                togglePropertyAddDialog = { propertyViewModel.togglePropertyAddDialog() },
                handlePropertyAdd = { propertyViewModel.handlePropertyAdd() },
                currentPartnership = propertyViewModel.currentPartnership,
                isAddPropertyDialogOpen = propertyViewModel.isAddPropertyDialogOpen,
                loginCredentials = authViewModel.loginCredentials,
                updateLoginStateEmail = { authViewModel.updateLoginState(email = it) },
                updateLoginStatePassword = { authViewModel.updateLoginState(password = it) },
                login = { authViewModel.login() },
            ) {
                navController.navigate(
                    "$it"
                )
            }
        }
        // Account
        composable("Account") {
            AccountScreen(userState = accountViewModel.user,
                handleUserSave = { accountViewModel.handleUserSave() },
                handleUserNameEdit = { accountViewModel.handleUserEdit(name = it) },
                handleUserLastNameEdit = { accountViewModel.handleUserEdit(lastName = it) },
                handleUserEmailEdit = { accountViewModel.handleUserEdit(email = it) },
                handleUserStreetEdit = { accountViewModel.handleUserEdit(street = it) },
                handleUserStreetNumberEdit = { accountViewModel.handleUserEdit(streetNumber = it) },
                handleUserZipCodeEdit = { accountViewModel.handleUserEdit(zipCode = it) },
                handleUserCityEdit = { accountViewModel.handleUserEdit(city = it) },
                handleUserCountryEdit = { accountViewModel.handleUserEdit(country = it) },
                partnershipList = propertyViewModel.partnershipListState,
                getData = { accountViewModel.getData() },
                uiState = accountViewModel.uiState,
                navigateTo = {
                    navController.navigate(
                        "$it"
                    )
                })
        }


        // Portfolio
        composable("PropertyDetail") {
            PropertyOverviewScreen(uiState = propertyViewModel.uiState,
                propertyState = propertyViewModel.propertyState,
                handlePropertyEditName = { propertyViewModel.handlePropertyEdit(propertyName = it) },
                handlePropertyEditType = { propertyViewModel.handlePropertyEdit(propertyType = it) },
                handlePropertyEditStreet = { propertyViewModel.handlePropertyEdit(street = it) },
                handlePropertyEditStreetNumber = { propertyViewModel.handlePropertyEdit(streetNumber = it) },
                handlePropertyEditZipCode = { propertyViewModel.handlePropertyEdit(zipCode = it) },
                handlePropertyEditCity = { propertyViewModel.handlePropertyEdit(city = it) },
                handlePropertyEditCountry = { propertyViewModel.handlePropertyEdit(country = it) },
                clearImage = { propertyViewModel.clearImage() },
                uploadImage = { context, uri ->
                    if (uri != null) {
                        propertyViewModel.uploadImage(context, uri)
                    }
                },
                propertyDocuments = propertyViewModel.propertyDocuments,
                handleDocumentDelete = { propertyViewModel.handleDocumentDelete(it) },
                downloadFile = { propertyViewModel.downloadFile(it) },
                handleAddDocumentDateEdit = { propertyViewModel.handleAddDocumentEdit(expiryDate = it) },
                handleAddDocumentTypeEdit = { propertyViewModel.handleAddDocumentEdit(documentType = it) },
                propertyDocumentState = propertyViewModel.propertyDocument,
                uploadDocument = { context, uri -> propertyViewModel.uploadDocument(context, uri) },
                propertyPremises = propertyViewModel.propertyPremises,
                getDataForActiveProperty = { propertyViewModel.getDataForActiveProperty() },
                saveDataForActiveProperty = { propertyViewModel.handlePropertySave() },
                navigateTo = {
                    navController.navigate(
                        "$it"
                    )
                })


        }
    }
}
