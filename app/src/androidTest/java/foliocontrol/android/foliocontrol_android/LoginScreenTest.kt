package foliocontrol.android.foliocontrol_android

import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onNodeWithText
import foliocontrol.android.foliocontrolandroid.domain.LoginCredentials
import foliocontrol.android.foliocontrolandroid.screens.LoginScreen
import org.junit.Rule
import org.junit.Test

class LoginScreenTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testLoginScreen() {
        composeTestRule.setContent {
            LoginScreen(
                errorName = "",
                loginCredentials = LoginCredentials("test", "test"),
                updateLoginStateEmail = {},
                updateLoginStatePassword = {},
                login = {},

                )
        }

        composeTestRule.onAllNodesWithText("Sign In", ignoreCase = true, substring = true)[0].assertExists()
        composeTestRule.onNodeWithText("Email", ignoreCase = true, substring = true).assertExists()
        composeTestRule.onNodeWithText("Password",ignoreCase = true, substring = true).assertExists()
        composeTestRule.onNodeWithText("Login",ignoreCase = true, substring = true).assertHasClickAction()
    }
}