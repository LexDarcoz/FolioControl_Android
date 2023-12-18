package foliocontrol.android.foliocontrolandroid.ui.screens.portfolio

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.unit.dp
import foliocontrol.android.foliocontrolandroid.domain.Premise
import foliocontrol.android.foliocontrolandroid.ui.components.PremiseItem
import foliocontrol.android.foliocontrolandroid.ui.viewModels.PropertyViewModel

@Composable
fun PropertyPremisesScreen(propertyViewModel: PropertyViewModel, offline: Boolean = false) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        when {
            offline -> OfflineScreen()
            propertyViewModel.propertyPremises.isEmpty() -> EmptyListScreen()
            else -> PremisesListScreen(propertyViewModel.propertyPremises)
        }
    }
}

@Composable
fun OfflineScreen() {
    // Customize this composable to show the offline screen
    Box(
        modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
    ) {
        Text(text = "You are offline. Please check your internet connection.")
    }
}

@Composable
fun EmptyListScreen() {
    // Customize this composable to show the screen when premises list is empty
    Box(
        modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
    ) {
        Text(text = "No premises available.")
    }
}

@Composable
fun PremisesListScreen(propertyPremises: List<Premise>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        items(propertyPremises) { premise ->
            PremiseItem(premise)
        }
    }
}