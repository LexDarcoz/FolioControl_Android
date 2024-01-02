package foliocontrol.android.foliocontrol_android

import PropertyDetailScreen
import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import foliocontrol.android.foliocontrolandroid.domain.Property
import org.junit.Rule
import org.junit.Test

class PropertyDetailScreenTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testAccountScreen() {
        composeTestRule.setContent {
            PropertyDetailScreen(
                saveProperty = {},
                propertyState =
                    Property(
                        0,
                        "De poorter",
                        "Apartment",
                        "yes.img",
                        "straat",
                        "straatnr",
                        "zip",
                        "stad",
                        "country",
                    ),
                handlePropertyEditName = {},
                handlePropertyEditType = {},
                handlePropertyEditStreet = {},
                handlePropertyEditStreetNumber = {},
                handlePropertyEditZipCode = {},
                handlePropertyEditCity = {},
                handlePropertyEditCountry = {},
                navigateTo = {},
                offline = false,
            )
        }

        composeTestRule.onNodeWithText(
            "Details",
            ignoreCase = true,
            substring = true,
        ).assertExists()
        composeTestRule.onNodeWithText(
            "Apartment",
            ignoreCase = true,
            substring = true,
        ).assertExists()
        composeTestRule.onNodeWithText(
            "Details - De poorter",
            ignoreCase = true,
        ).assertExists()
        composeTestRule.onNodeWithText(
            "City",
            ignoreCase = true,
            substring = true,
        ).assertExists()
        composeTestRule.onNodeWithText(
            "zip",
        ).assertExists()
        composeTestRule.onNodeWithText(
            "Save",
        ).assertExists().assertHasClickAction()
    }

    @Test
    fun testAccountScreenOffline() {
        composeTestRule.setContent {
            PropertyDetailScreen(
                saveProperty = {},
                propertyState =
                    Property(
                        0,
                        "De poorter",
                        "Apartment",
                        "yes.img",
                        "straat",
                        "straatnr",
                        "zip",
                        "stad",
                        "country",
                    ),
                handlePropertyEditName = {},
                handlePropertyEditType = {},
                handlePropertyEditStreet = {},
                handlePropertyEditStreetNumber = {},
                handlePropertyEditZipCode = {},
                handlePropertyEditCity = {},
                handlePropertyEditCountry = {},
                navigateTo = {},
                offline = true,
            )
        }

        composeTestRule.onNodeWithText(
            "Details",
            ignoreCase = true,
            substring = true,
        ).assertExists()
        composeTestRule.onNodeWithText(
            "Apartment",
            ignoreCase = true,
            substring = true,
        ).assertExists()
        composeTestRule.onNodeWithText(
            "Details - De poorter",
            ignoreCase = true,
        ).assertExists()
        composeTestRule.onNodeWithText(
            "City",
            ignoreCase = true,
            substring = true,
        ).assertExists()
        composeTestRule.onNodeWithText(
            "zip",
        ).assertExists()
        composeTestRule.onNodeWithText(
            "Offline preview",
            ignoreCase = true,
            substring = true,
        ).assertExists()
    }
}
