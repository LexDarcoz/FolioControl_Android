package foliocontrol.android.foliocontrolandroid.ui.screens.portfolio

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FileUpload
import androidx.compose.material.icons.filled.WifiOff
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import foliocontrol.android.foliocontrolandroid.domain.PropertyDocument
import foliocontrol.android.foliocontrolandroid.ui.components.OfflineScreen
import foliocontrol.android.foliocontrolandroid.ui.components.card.DocumentCard
import foliocontrol.android.foliocontrolandroid.ui.viewModels.PropertyViewModel
import foliocontrol.android.foliocontrolandroid.ui.viewModels.common.EmptyListScreen

@Composable
fun PropertyDocumentsScreen(propertyViewModel: PropertyViewModel, offline: Boolean = false) {
    Box(
        modifier = Modifier.fillMaxSize().padding(16.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Text(
                text = "Documents Overview",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )

            when {
                offline -> OfflineScreen()
                propertyViewModel.propertyDocuments.isEmpty() -> EmptyListScreen(
                    "No documents available."
                )

                else -> PropertyList(propertyViewModel.propertyDocuments, propertyViewModel)
            }
            Button(
                enabled = !offline,
                onClick = {
                    // TODO
                },
                modifier = Modifier.fillMaxWidth().padding(vertical = 16.dp)
            ) {
                if (offline) {
                    Row {
                        Icon(
                            Icons.Default.WifiOff,
                            contentDescription = null,
                            modifier = Modifier.padding(end = 8.dp)
                        )
                        Text(text = "Offline preview")
                    }
                } else {
                    Row {
                        Icon(
                            Icons.Default.FileUpload,
                            contentDescription = null,
                            modifier = Modifier.padding(end = 8.dp)
                        )
                        Text(text = "Upload document ")
                    }
                }
            }
        }
    }
}

@Composable
fun PropertyList(propertyDocuments: List<PropertyDocument>, propertyViewModel: PropertyViewModel) {
    Box(
        modifier = Modifier.fillMaxWidth().fillMaxHeight(0.8f).padding(top = 32.dp)
    ) {
        LazyColumn {
            items(propertyDocuments) { document ->
                DocumentCard(document, propertyViewModel)
            }
        }
    }
}
