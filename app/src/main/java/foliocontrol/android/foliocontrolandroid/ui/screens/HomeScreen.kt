package foliocontrol.android.foliocontrolandroid.screens

import PropertyCard
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import foliocontrol.android.foliocontrolandroid.domain.viewModels.AuthViewModel
import foliocontrol.android.foliocontrolandroid.domain.viewModels.PropertyViewModel

@Composable
fun HomeScreen(
    authViewModel: AuthViewModel,
    propertyViewModel: PropertyViewModel,
    navigateTo: (Any?) -> Unit = {}
) {
    var loading by remember { mutableStateOf(false) }
    fun handleClick() {
    }
    DisposableEffect(propertyViewModel.partnershipList) {
        propertyViewModel.getData()

        onDispose { }
    }

    Scaffold(floatingActionButton = {
        FloatingActionButton(onClick = { handleClick() }) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.secondary
            )
        }
    }) { values ->

        LazyColumn(contentPadding = values) {
            if (propertyViewModel.propertyListState.isEmpty()) {
                item {
                    AddPropertyCard(navigateTo = navigateTo)
                }
            } else {
                items(propertyViewModel.propertyListState) { property ->
                    PropertyCard(
                        propertyViewModel = propertyViewModel,
                        property = property,
                        navigateTo = navigateTo
                    )
                }
            }
        }
    }
}

@Composable
fun AddPropertyCard(
    navigateTo: (Any?) -> Unit = {}
) {
    Box(
        modifier = Modifier.height(150.dp)
            .fillMaxWidth() // Use fillMaxWidth() instead of fillMaxSize()
            .background(MaterialTheme.colorScheme.surface) // Optional: Add a background color
            .padding(16.dp)
    ) {
        Text(text = "No data found", textAlign = TextAlign.Center)
    }
}
