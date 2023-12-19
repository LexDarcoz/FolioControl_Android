package foliocontrol.android.foliocontrolandroid.ui.screens.account

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import foliocontrol.android.foliocontrolandroid.ui.components.PartnershipCard
import foliocontrol.android.foliocontrolandroid.ui.viewModels.PropertyViewModel

@Composable
fun AccountPartnershipScreen(
    propertyViewModel: PropertyViewModel
) {
    val partnerships = propertyViewModel.partnershipListState

    LazyColumn {
        items(partnerships) { partnership ->
            PartnershipCard(partnership = partnership)
        }
    }
}
