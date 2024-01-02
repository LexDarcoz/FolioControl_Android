package foliocontrol.android.foliocontrol_android

import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import foliocontrol.android.foliocontrolandroid.domain.Partnership
import foliocontrol.android.foliocontrolandroid.domain.Property
import foliocontrol.android.foliocontrolandroid.screens.HomeScreen
import foliocontrol.android.foliocontrolandroid.ui.viewModels.common.UiState
import junit.framework.TestCase.assertEquals
import org.junit.Rule
import org.junit.Test

class HomeScreenTest {
    @get:Rule
    val composeTestRule = createComposeRule()
    var clicked = false

    @Test
    fun testHomeScreenOnline() {
        composeTestRule.setContent {
            var fakeList = listOf<Property>()

            var testProperty =
                Property(
                    0,
                    "Kot Gent",
                    "House",
                    "Korenmarkt",
                    "1",
                    "Belgium",
                    "House",
                    "1",
                    "1",
                    "1",
                    "1",
                    "1",
                    1,
                )
            fakeList = fakeList + testProperty

            HomeScreen(
                getData = {},
                toggleSearchBar = {},
                isSearchBarEnabled = false,
                filteredList = listOf(),
                filterProperties = {},
                propertyListState = fakeList,
                uiState = UiState.Success("Succesfully loaded data"),
                addPropertyState = Property(),
                handlePropertyNameEdit = {},
                handlePropertyTypeEdit = {},
                handlePropertyStreetAddEdit = {},
                handlePropertyStreetNumberAddEdit = {},
                handlePropertyZipCodeAddEdit = {},
                handlePropertyCityAddEdit = {},
                handlePropertyCountryAddEdit = {},
                selectProperty = {},
                deleteProperty = {},
                togglePropertyAddDialog = {},
                handlePropertyAdd = {},
                currentPartnership = Partnership(),
                isAddPropertyDialogOpen = false,
                navigateTo = { destination ->
                    clicked = true
                },
            )
        }

        composeTestRule.onNodeWithText("Kot Gent", ignoreCase = true, substring = true)
            .assertExists()
        composeTestRule.onNodeWithText("House", ignoreCase = true, substring = true)
        composeTestRule.onNodeWithText("View Property", ignoreCase = true, substring = true)
            .assertHasClickAction().performClick()
        assertEquals(clicked, true)
    }

    @Test
    fun testHomeScreenOffline() {
        composeTestRule.setContent {
            var fakeList = listOf<Property>()
            var testProperty =
                Property(
                    0,
                    "Kot Gent",
                    "House",
                    "Korenmarkt",
                    "1",
                    "Belgium",
                    "Gent",
                    "1",
                    "1",
                    "1",
                    "1",
                    "1",
                    1,
                )
            fakeList = fakeList + testProperty

            HomeScreen(
                getData = {},
                toggleSearchBar = {},
                isSearchBarEnabled = false,
                filteredList = listOf(),
                filterProperties = {},
                propertyListState = fakeList,
                uiState = UiState.Offline("Succesfully loaded data from RoomDB"),
                addPropertyState = Property(),
                handlePropertyNameEdit = {},
                handlePropertyTypeEdit = {},
                handlePropertyStreetAddEdit = {},
                handlePropertyStreetNumberAddEdit = {},
                handlePropertyZipCodeAddEdit = {},
                handlePropertyCityAddEdit = {},
                handlePropertyCountryAddEdit = {},
                selectProperty = {},
                deleteProperty = {},
                togglePropertyAddDialog = {},
                handlePropertyAdd = {},
                currentPartnership = Partnership(),
                isAddPropertyDialogOpen = false,
                navigateTo = {},
            )
        }

        composeTestRule.onNodeWithText("Kot Gent", ignoreCase = true, substring = true)
            .assertExists()
        composeTestRule.onNodeWithText("View Property", ignoreCase = true, substring = true)
            .assertHasClickAction()
        composeTestRule.onNodeWithText(
            "House",
            ignoreCase = true,
            substring = true,
        ).assertExists()

        composeTestRule.onNodeWithText("Retry", ignoreCase = true, substring = true)
            .assertHasClickAction()
        composeTestRule.onNodeWithText(
            "No Internet Connection found.",
            ignoreCase = true,
            substring = true,
        ).assertExists()
    }

    @Test
    fun testHomeScreenLoading() {
        composeTestRule.setContent {
            var fakeList = listOf<Property>()

            var testProperty =
                Property(
                    0,
                    "Kot Gent",
                    "House",
                    "Korenmarkt",
                    "1",
                    "Belgium",
                    "House",
                    "1",
                    "1",
                    "1",
                    "1",
                    "1",
                    1,
                )
            fakeList = fakeList + testProperty

            HomeScreen(
                getData = {},
                toggleSearchBar = {},
                isSearchBarEnabled = false,
                filteredList = listOf(),
                filterProperties = {},
                propertyListState = fakeList,
                uiState = UiState.Loading,
                addPropertyState = Property(),
                handlePropertyNameEdit = {},
                handlePropertyTypeEdit = {},
                handlePropertyStreetAddEdit = {},
                handlePropertyStreetNumberAddEdit = {},
                handlePropertyZipCodeAddEdit = {},
                handlePropertyCityAddEdit = {},
                handlePropertyCountryAddEdit = {},
                selectProperty = {},
                deleteProperty = {},
                togglePropertyAddDialog = {},
                handlePropertyAdd = {},
                currentPartnership = Partnership(),
                isAddPropertyDialogOpen = false,
                navigateTo = {},
            )
        }

        composeTestRule.onNodeWithText("Kot Gent", ignoreCase = true, substring = true)
            .assertDoesNotExist()
        composeTestRule.onNodeWithText("View Property", ignoreCase = true, substring = true)
            .assertDoesNotExist()
        composeTestRule.onNodeWithText("House", ignoreCase = true, substring = true)
            .assertDoesNotExist()
        composeTestRule.onNodeWithText(
            "No network detected, previewing offline data",
            ignoreCase = true,
            substring = true,
        ).assertDoesNotExist()
        composeTestRule.onNodeWithText("Retry", ignoreCase = true, substring = true)
            .assertDoesNotExist()
        composeTestRule.onNodeWithText(
            "No Internet Connection found.",
            ignoreCase = true,
            substring = true,
        ).assertDoesNotExist()
    }

    @Test
    fun testHomeScreenError() {
        composeTestRule.setContent {
            HomeScreen(
                getData = {},
                toggleSearchBar = {},
                isSearchBarEnabled = false,
                filteredList = listOf(),
                filterProperties = {},
                propertyListState = listOf(),
                uiState = UiState.Error("Server is down"),
                addPropertyState = Property(),
                handlePropertyNameEdit = {},
                handlePropertyTypeEdit = {},
                handlePropertyStreetAddEdit = {},
                handlePropertyStreetNumberAddEdit = {},
                handlePropertyZipCodeAddEdit = {},
                handlePropertyCityAddEdit = {},
                handlePropertyCountryAddEdit = {},
                selectProperty = {},
                deleteProperty = {},
                togglePropertyAddDialog = {},
                handlePropertyAdd = {},
                currentPartnership = Partnership(),
                isAddPropertyDialogOpen = false,
                navigateTo = {},
            )
        }

        composeTestRule.onNodeWithText("Kot Gent", ignoreCase = true, substring = true)
            .assertDoesNotExist()
        composeTestRule.onNodeWithText("View Property", ignoreCase = true, substring = true)
            .assertDoesNotExist()
        composeTestRule.onNodeWithText("House", ignoreCase = true, substring = true)
            .assertDoesNotExist()
        composeTestRule.onNodeWithText(
            "No network detected, previewing offline data",
            ignoreCase = true,
            substring = true,
        ).assertDoesNotExist()
        composeTestRule.onNodeWithText("Retry", ignoreCase = true, substring = true)
            .assertHasClickAction()
        composeTestRule.onNodeWithText(
            "Server is down",
            ignoreCase = true,
            substring = true,
        ).assertExists()
    }

    @Test
    fun testHomeScreenAddDialogOpen() {
        composeTestRule.setContent {
            HomeScreen(
                getData = {},
                toggleSearchBar = {},
                isSearchBarEnabled = false,
                filteredList = listOf(),
                filterProperties = {},
                propertyListState = listOf(),
                uiState = UiState.Success("Server is up"),
                addPropertyState = Property(),
                handlePropertyNameEdit = {},
                handlePropertyTypeEdit = {},
                handlePropertyStreetAddEdit = {},
                handlePropertyStreetNumberAddEdit = {},
                handlePropertyZipCodeAddEdit = {},
                handlePropertyCityAddEdit = {},
                handlePropertyCountryAddEdit = {},
                selectProperty = {},
                deleteProperty = {},
                togglePropertyAddDialog = {},
                handlePropertyAdd = {},
                currentPartnership = Partnership(),
                isAddPropertyDialogOpen = true,
                navigateTo = {},
            )
        }

        composeTestRule.onNodeWithText("Name", ignoreCase = true, substring = true).assertExists()
        composeTestRule.onNodeWithText("Country", ignoreCase = true, substring = true)
            .assertExists()
        composeTestRule.onNodeWithText("Street Number", ignoreCase = true).assertExists()
        composeTestRule.onNodeWithText(
            "Type",
            ignoreCase = true,
            substring = true,
        ).assertExists()
        composeTestRule.onNodeWithText("Save", ignoreCase = true, substring = true)
            .assertHasClickAction()
        composeTestRule.onNodeWithText("Dismiss", ignoreCase = true, substring = true)
            .assertHasClickAction()
    }

    @Test
    fun testHomeScreenSearchBarUnlocked() {
        composeTestRule.setContent {
            HomeScreen(
                getData = {},
                toggleSearchBar = {},
                isSearchBarEnabled = true,
                filteredList = listOf(),
                filterProperties = {},
                propertyListState = listOf(),
                uiState = UiState.Success("Server is up"),
                addPropertyState = Property(),
                handlePropertyNameEdit = {},
                handlePropertyTypeEdit = {},
                handlePropertyStreetAddEdit = {},
                handlePropertyStreetNumberAddEdit = {},
                handlePropertyZipCodeAddEdit = {},
                handlePropertyCityAddEdit = {},
                handlePropertyCountryAddEdit = {},
                selectProperty = {},
                deleteProperty = {},
                togglePropertyAddDialog = {},
                handlePropertyAdd = {},
                currentPartnership = Partnership(),
                isAddPropertyDialogOpen = true,
                navigateTo = {},
            )
        }

        composeTestRule.onNodeWithText("Search...", ignoreCase = true, substring = true)
            .assertExists()
    }
}
