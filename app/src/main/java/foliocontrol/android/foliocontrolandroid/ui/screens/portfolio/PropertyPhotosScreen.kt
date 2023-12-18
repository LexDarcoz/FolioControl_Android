package foliocontrol.android.foliocontrolandroid.ui.screens.portfolio

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import coil.compose.AsyncImage
import foliocontrol.android.foliocontrolandroid.data.remote.common.Constants
import foliocontrol.android.foliocontrolandroid.ui.viewModels.PropertyViewModel

@Composable
fun PropertyPhotosScreen(propertyViewModel: PropertyViewModel, offline: Boolean = false) {

    val imageUrl = Constants.PROPERTYPHOTOS_URL;
    Box(
        modifier = Modifier.fillMaxSize()

    ) {
        if (propertyViewModel.propertyState.propertyImg == "null") {
            AsyncImage(
                model = "${imageUrl}/default.avif",
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.3f)
            )
        } else {
            AsyncImage(
                model = "${imageUrl}/${propertyViewModel.propertyState.propertyImg}",
                contentDescription = "${propertyViewModel.propertyState.propertyName} image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.3f)
            )
        }


        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Text(text = "Property Photos screen", style = MaterialTheme.typography.displayLarge)
        }
    }

}