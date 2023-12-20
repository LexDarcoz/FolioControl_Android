package foliocontrol.android.foliocontrolandroid.ui.screens.portfolio

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import foliocontrol.android.foliocontrolandroid.domain.PropertyDocument
import foliocontrol.android.foliocontrolandroid.ui.components.cards.DocumentCard
import foliocontrol.android.foliocontrolandroid.ui.components.OfflineScreen
import foliocontrol.android.foliocontrolandroid.ui.viewModels.PropertyViewModel
import foliocontrol.android.foliocontrolandroid.ui.viewModels.common.EmptyListScreen

@Composable
fun PropertyDocumentsScreen(propertyViewModel: PropertyViewModel, offline: Boolean = false) {
    Box(
        modifier = Modifier.fillMaxSize().padding(16.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)
        ) {
            Text(
                text = "Documents Overview",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
        }
        when {
            offline -> OfflineScreen()
            propertyViewModel.propertyDocuments.isEmpty() -> EmptyListScreen(
                "No documents available."
            )

            else -> PropertyList(propertyViewModel.propertyDocuments)
        }
    }
}

@Composable
fun PropertyList(propertyDocuments: List<PropertyDocument>) {
    Box(
        modifier = Modifier.fillMaxWidth().padding(top = 32.dp).fillMaxHeight().clickable {
            // TODO: Handle the click event (e.g., download documents from the server)
        }
    ) {
        LazyColumn {
            items(propertyDocuments) { document ->
                DocumentCard(document)
            }
        }
    }
}
