package foliocontrol.android.foliocontrolandroid.ui.screens.account

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import foliocontrol.android.foliocontrolandroid.domain.Partnership
import foliocontrol.android.foliocontrolandroid.ui.viewModels.AccountViewModel
import foliocontrol.android.foliocontrolandroid.ui.viewModels.PropertyViewModel

@Composable
fun AccountPartnershipScreen(
    accountViewModel: AccountViewModel,
    propertyViewModel: PropertyViewModel,
    navigateTo: (Any?) -> Unit = {}
) {
    val partnerships = propertyViewModel.partnershipListState

    LazyColumn {
        items(partnerships) { partnership ->
            PartnershipRow(partnership = partnership)
        }
    }
}

@Composable
fun PartnershipRow(partnership: Partnership) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        shape = MaterialTheme.shapes.small,
        colors = CardDefaults.cardColors(
            contentColor = MaterialTheme.colorScheme.primary,
            containerColor = MaterialTheme.colorScheme.secondary
        ),
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(text = partnership.name, fontWeight = FontWeight.Bold, fontSize = 18.sp)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Country: ${partnership.country}")
            Text(text = "City: ${partnership.city}")
            Text(text = "Street: ${partnership.street} ${partnership.streetNumber}")
            Text(text = "ZIP Code: ${partnership.zipCode}")
        }
    }
}
