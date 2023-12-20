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
import foliocontrol.android.foliocontrolandroid.ui.components.card.PartnershipCard
import foliocontrol.android.foliocontrolandroid.ui.viewModels.PropertyViewModel

@Composable
fun AccountPartnershipScreen(
    propertyViewModel: PropertyViewModel
) {
    val partnerships = propertyViewModel.partnershipListState
    Box(
        modifier = Modifier.fillMaxSize().padding(8.dp)
    ) {
        Column {
            Text(
                text = "Partnerships Overview",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            LazyColumn {
                items(partnerships) { partnership ->
                    PartnershipCard(partnership = partnership)
                }
            }
        }
    }
}
