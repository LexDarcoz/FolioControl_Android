package foliocontrol.android.foliocontrol_android

import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onNodeWithText
import foliocontrol.android.foliocontrolandroid.domain.User
import foliocontrol.android.foliocontrolandroid.ui.screens.account.AccountDetailScreen
import org.junit.Rule
import org.junit.Test

class AccountScreenTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testAccountScreen() {
        composeTestRule.setContent {
            AccountDetailScreen(
                userState =
                    User(
                        0,
                        "alias",
                        "naam",
                        "achternaam",
                        "straat",
                        "straatnr",
                        "zip",
                        "stad",
                        "country",
                    ),
                handleUserSave = {},
                handleUserNameEdit = {},
                handleUserLastNameEdit = {},
                handleUserEmailEdit = {},
                handleUserStreetEdit = {},
                handleUserStreetNumberEdit = {},
                handleUserZipCodeEdit = {},
                handleUserCityEdit = {},
                handleUserCountryEdit = {},
                offline = false,
                navigateTo = {},
            )
        }

        composeTestRule.onAllNodesWithText(
            "Details:",
            ignoreCase = true,
            substring = true,
        )[0].assertExists()
        composeTestRule.onNodeWithText("Last Name", ignoreCase = true, substring = true)
            .assertExists()
        composeTestRule.onNodeWithText("Email", ignoreCase = true, substring = true).assertExists()
        composeTestRule.onNodeWithText("Save", ignoreCase = true, substring = true)
            .assertHasClickAction()
    }

    @Test
    fun testAccountScreenOffline() {
        composeTestRule.setContent {
            AccountDetailScreen(
                userState =
                    User(
                        0,
                        "alias",
                        "naam",
                        "achternaam",
                        "straat",
                        "straatnr",
                        "zip",
                        "stad",
                        "country",
                    ),
                handleUserSave = {},
                handleUserNameEdit = {},
                handleUserLastNameEdit = {},
                handleUserEmailEdit = {},
                handleUserStreetEdit = {},
                handleUserStreetNumberEdit = {},
                handleUserZipCodeEdit = {},
                handleUserCityEdit = {},
                handleUserCountryEdit = {},
                offline = true,
                navigateTo = {},
            )
        }

        composeTestRule.onAllNodesWithText(
            "Details:",
            ignoreCase = true,
            substring = true,
        )[0].assertExists()
        composeTestRule.onNodeWithText("Last Name", ignoreCase = true, substring = true)
            .assertExists()
        composeTestRule.onNodeWithText("Email", ignoreCase = true, substring = true).assertExists()
        composeTestRule.onNodeWithText("Offline preview", ignoreCase = true, substring = true)
            .assertHasClickAction()

        composeTestRule.onAllNodesWithText(
            "naam",
            ignoreCase = true,
            substring = true,
        )[0].assertExists()
        composeTestRule.onNodeWithText("achternaam", ignoreCase = true, substring = true)
            .assertExists()
        composeTestRule.onNodeWithText("straatnr", ignoreCase = true, substring = true)
            .assertExists()
        composeTestRule.onNodeWithText("Offline preview", ignoreCase = true, substring = true)
            .assertHasClickAction()
    }
}
