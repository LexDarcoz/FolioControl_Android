package foliocontrol.android.foliocontrolandroid.ui.screens.account

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import foliocontrol.android.foliocontrolandroid.ui.components.cards.PartnershipCard
import foliocontrol.android.foliocontrolandroid.ui.viewModels.PropertyViewModel

@Composable
fun AccountPartnershipScreen(
    propertyViewModel: PropertyViewModel
) {
    val partnerships = propertyViewModel.partnershipListState

    Text(
        text = "Partnership Overview",
        style = MaterialTheme.typography.titleMedium,
        fontWeight = FontWeight.Bold
    )

    LazyColumn {
        items(partnerships) { partnership ->
            PartnershipCard(partnership = partnership)
        }
    }
}
