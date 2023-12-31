package foliocontrol.android.foliocontrolandroid.ui.components.card

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Apartment
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import foliocontrol.android.foliocontrolandroid.domain.Partnership

/**
 * Composable function representing a card for displaying partnership information.
 *
 * @param partnership The [Partnership] data to be displayed on the card.
 */
@Composable
fun PartnershipCard(partnership: Partnership) {
    Card(
        modifier =
            Modifier
                .fillMaxWidth()
                .padding(16.dp),
        shape = MaterialTheme.shapes.small,
        colors =
            CardDefaults.cardColors(
                contentColor = MaterialTheme.colorScheme.primary,
                containerColor = MaterialTheme.colorScheme.secondary,
            ),
        elevation = CardDefaults.cardElevation(8.dp),
    ) {
        Column(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
        ) {
            Row {
                Icon(
                    imageVector = Icons.Default.Apartment,
                    contentDescription = "Partnerships",
                    Modifier.padding(end = 8.dp),
                )
                Text(
                    text = partnership.name,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Country: ${partnership.country}",
                color = MaterialTheme.colorScheme.onSurface,
            )
            Text(text = "City: ${partnership.city}", color = MaterialTheme.colorScheme.onSurface)
            Text(
                text = "Street: ${partnership.street} ${partnership.streetNumber}",
                color = MaterialTheme.colorScheme.onSurface,
            )
            Text(
                text = "ZIP Code: ${partnership.zipCode}",
                color = MaterialTheme.colorScheme.onSurface,
            )
            Text(
                text = "VAT: ${partnership.vatNumber}",
                color = MaterialTheme.colorScheme.onSurface,
            )
        }
    }
}
