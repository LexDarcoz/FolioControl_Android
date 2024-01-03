package foliocontrol.android.foliocontrol_android

import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.unit.dp
import foliocontrol.android.foliocontrolandroid.domain.Property
import foliocontrol.android.foliocontrolandroid.ui.screens.portfolio.PropertyPhotosScreen
import foliocontrol.android.foliocontrolandroid.ui.viewModels.common.UiState
import foliocontrol.android.foliocontrolandroid.ui.viewModels.common.WindowInfo
import org.junit.Rule
import org.junit.Test

class PropertyPhotosScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testAccountScreen() {
        composeTestRule.setContent {
            PropertyPhotosScreen(
                getDataForActiveProperty = {},
                clearImage = {},
                uploadImage = { _, _ -> },
                propertyState = Property(
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
                uiState = UiState.Success("Succesfully retrieved"),
                windowInfo = WindowInfo(
                    WindowInfo.WindowType.Compact, WindowInfo.WindowType.Compact, 650.dp, 650.dp
                ),
                offline = false


            )
        }

        composeTestRule.onNodeWithText(
            "Photos:", ignoreCase = true, substring = true
        ).assertExists()
        composeTestRule.onNodeWithContentDescription(
            "Property Image", ignoreCase = true, substring = true
        ).assertExists()
        composeTestRule.onNodeWithText(
            "Select Image"
        ).assertExists().assertHasClickAction()
    }

    @Test
    fun testAccountScreenOffline() {
        composeTestRule.setContent {
            PropertyPhotosScreen(
                getDataForActiveProperty = {},
                clearImage = {},
                uploadImage = { _, _ -> },
                propertyState = Property(
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
                uiState = UiState.Offline("Succesfully retrieved from roomdb"),
                windowInfo = WindowInfo(
                    WindowInfo.WindowType.Compact, WindowInfo.WindowType.Compact, 650.dp, 650.dp
                ),
                offline = true


            )
        }

        composeTestRule.onNodeWithText(
            "Photos:", ignoreCase = true, substring = true
        ).assertExists()
        composeTestRule.onNodeWithContentDescription(
            "Property Image", ignoreCase = true, substring = true
        ).assertExists()
        composeTestRule.onNodeWithText(
            "Offline preview", ignoreCase = true, substring = true
        ).assertExists().assertHasClickAction()
    }
}