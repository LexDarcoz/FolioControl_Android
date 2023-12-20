package foliocontrol.android.foliocontrolandroid.ui.screens.portfolio

import android.net.Uri
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Image
import androidx.compose.material.icons.filled.WifiOff
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import foliocontrol.android.foliocontrolandroid.data.remote.common.Constants
import foliocontrol.android.foliocontrolandroid.ui.components.dialogs.ImageDialog
import foliocontrol.android.foliocontrolandroid.ui.viewModels.PropertyViewModel

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun PropertyPhotosScreen(propertyViewModel: PropertyViewModel, offline: Boolean = false) {
    val imageUrl = Constants.PROPERTYPHOTOS_URL
    val defaultImage = "$imageUrl/default.avif"
    val propertyImg = propertyViewModel.propertyState.propertyImg.ifEmpty { "null" }
    var selectedImageUri by rememberSaveable { mutableStateOf<Uri?>(null) }

    val image = when (propertyImg) {
        "null" -> defaultImage
        else -> "$imageUrl/$propertyImg"
    }

    Box(
        modifier = Modifier.fillMaxSize().padding(16.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize()

        ) {
            Text(
                text = "Photos Overview",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )

            Box(
                modifier = Modifier.fillMaxWidth().fillMaxHeight(0.8f).padding(top = 32.dp)
            ) {
                GlideImage(
                    model = image,
                    contentDescription = "Property Image",
                    contentScale = ContentScale.Fit,
                    modifier = Modifier.fillMaxSize()
                )
            }
            Button(
                enabled = !offline,
                onClick = {
                    propertyViewModel.handlePropertySave()
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
                            Icons.Default.Image,
                            contentDescription = null,
                            modifier = Modifier.padding(end = 8.dp)
                        )
                        Text(text = "Upload photo")
                    }
                }
            }
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
