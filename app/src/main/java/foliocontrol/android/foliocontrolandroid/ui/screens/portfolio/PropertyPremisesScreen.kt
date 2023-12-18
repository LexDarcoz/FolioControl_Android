package foliocontrol.android.foliocontrolandroid.ui.screens.portfolio

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import foliocontrol.android.foliocontrolandroid.domain.Premise
import foliocontrol.android.foliocontrolandroid.ui.components.EmptyListScreen
import foliocontrol.android.foliocontrolandroid.ui.components.OfflineScreen
import foliocontrol.android.foliocontrolandroid.ui.components.PremiseItem
import foliocontrol.android.foliocontrolandroid.ui.viewModels.PropertyViewModel

@Composable
fun PremisesListScreen(propertyViewModel: PropertyViewModel, offline: Boolean = false) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        ) {
            Text(
                text = "Property Photos Screen",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
        }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 32.dp)
                .fillMaxHeight()
                .clickable {
                    // TODO: Handle the click event
                },

            ) {
            when {
                offline -> OfflineScreen()
                propertyViewModel.propertyPremises.isEmpty() -> EmptyListScreen("No premises available.")
                else -> PremisesList(propertyViewModel.propertyPremises)
            }
        }
    }

}


@Composable
fun PremisesList(propertyPremises: List<Premise>) {
    LazyColumn(
        modifier = Modifier

    ) {
        items(propertyPremises) { premise ->
            PremiseItem(premise)
        }
    }
}