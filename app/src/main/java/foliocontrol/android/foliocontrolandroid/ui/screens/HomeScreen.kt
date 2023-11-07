package foliocontrol.android.foliocontrolandroid.screens

import PropertyCard
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import foliocontrol.android.foliocontrol_android.R
import foliocontrol.android.foliocontrolandroid.domain.viewModels.AuthViewModel
import foliocontrol.android.foliocontrolandroid.domain.viewModels.PropertyViewModel

@Composable
fun HomeScreen(
    authViewModel: AuthViewModel,
    propertyViewModel: PropertyViewModel,
    navigateTo: (Any?) -> Unit = {}
) {
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
    val propertyTypesIcons: Map<String, ImageVector> = mapOf(
        "Type1" to Icons.Default.Home
    )
    Card(

        modifier = Modifier.padding(8.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        shape = MaterialTheme.shapes.small,
        colors = CardDefaults.cardColors(

            contentColor = MaterialTheme.colorScheme.primary,
            containerColor = MaterialTheme.colorScheme.secondary
        )
    ) {
        Box(
            modifier = Modifier.height(150.dp).fillMaxSize()
        ) {
            Image(
                painter = rememberAsyncImagePainter(model = R.drawable.ic_default),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
            Icon(
                imageVector = Icons.Default.Home,
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier.size(40.dp).align(Alignment.BottomStart).padding(8.dp)
            )
        }
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = "No data found",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Text(
                text = "No data found",
                style = MaterialTheme.typography.bodySmall
            )
            Text(
                text = "No data found",
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(top = 8.dp)
            )
            Button(
                onClick = {
                    navigateTo("PropertyDetail")
                },
                modifier = Modifier.fillMaxWidth().padding(top = 4.dp)
            ) {
                Text(text = "Add property")
            }
        }
    }
}
