package foliocontrol.android.foliocontrolandroid.ui.screens.account

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import foliocontrol.android.foliocontrolandroid.domain.Partnership
import foliocontrol.android.foliocontrolandroid.ui.components.card.PartnershipCard

/**
 * Composable function representing the screen displaying a list of partnerships associated with the user's account.
 *
 * @param partnershipList The list of partnerships to be displayed.
 */
@Composable
fun AccountPartnershipScreen(partnershipList: List<Partnership>) {
    Box(
        modifier = Modifier.fillMaxSize(),
    ) {
        Column {
            Text(
                text = "Partnerships:",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(8.dp),
            )

            LazyColumn {
                items(
                    items = partnershipList,
                    key = { partnership -> partnership.partnershipID },
                ) { partnership ->
                    PartnershipCard(partnership = partnership)
                }
            }
        }
    }
}
