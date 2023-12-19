package foliocontrol.android.foliocontrolandroid.ui.screens.portfolio

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import foliocontrol.android.foliocontrolandroid.data.remote.common.Constants
import foliocontrol.android.foliocontrolandroid.ui.components.dialogs.ImageDialog
import foliocontrol.android.foliocontrolandroid.ui.viewModels.PropertyViewModel

@Composable
fun PropertyPhotosScreen(propertyViewModel: PropertyViewModel, offline: Boolean = false) {
    val imageUrl = Constants.PROPERTYPHOTOS_URL
    val defaultImage = "$imageUrl/default.avif"
    val propertyImg = propertyViewModel.propertyState.propertyImg.ifEmpty { "null" }

    val image = when (propertyImg) {
        "null" -> defaultImage
        else -> "$imageUrl/$propertyImg"
    }

    Box(
        modifier = Modifier.fillMaxSize().padding(16.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)
        ) {
            Text(
                text = "Property Photos Screen",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
        }
        Box(
            modifier = Modifier.fillMaxWidth().padding(top = 32.dp).fillMaxHeight(0.6f).clickable {
                propertyViewModel.isAddPropertyDialogOpen = true
            }
        ) {
            AsyncImage(
                model = image,
                contentDescription = "Property Image",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
        }
    }

    when {
        propertyViewModel.isAddPropertyDialogOpen -> {
            ImageDialog(
                onDismissRequest = {
                    propertyViewModel.isAddPropertyDialogOpen = false
                },
                imageUrl = image
            )
        }
    }
}
