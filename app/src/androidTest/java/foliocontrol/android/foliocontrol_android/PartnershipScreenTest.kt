package foliocontrol.android.foliocontrol_android

import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onNodeWithText
import foliocontrol.android.foliocontrolandroid.domain.User
import foliocontrol.android.foliocontrolandroid.ui.screens.account.AccountDetailScreen
import foliocontrol.android.foliocontrolandroid.ui.screens.account.AccountPartnershipScreen
import org.junit.Rule
import org.junit.Test

class PartnershipScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testAccountScreen() {
        composeTestRule.setContent {
            AccountPartnershipScreen(
                emptyList()


            )
        }

        composeTestRule.onAllNodesWithText(
            "Partnerships:", ignoreCase = true, substring = true
        )[0].assertExists()

    }

}