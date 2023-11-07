package foliocontrol.android.foliocontrolandroid.screens

import PropertyCard
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import foliocontrol.android.foliocontrolandroid.domain.viewModels.PropertyViewModel

@Composable
fun HomeScreen(
    propertyViewModel: PropertyViewModel,
    navigateTo: (Any?) -> Unit = {}
) {
    fun handleClick() {
        println("aaa")
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
