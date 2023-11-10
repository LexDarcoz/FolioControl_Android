import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import foliocontrol.android.foliocontrol_android.R
import foliocontrol.android.foliocontrolandroid.domain.Property
import foliocontrol.android.foliocontrolandroid.ui.viewModels.PropertyViewModel

@Composable
fun PropertyCard(
    propertyViewModel: PropertyViewModel,
    property: Property,
    navigateTo: (Any?) -> Unit = {}
) {
    val propertyTypesIcons: Map<String, ImageVector> = mapOf(
        "Type1" to Icons.Default.Home,
        "Type2" to Icons.Default.Home,
        "Type3" to Icons.Default.Home,
        "Type4" to Icons.Default.Home
        // Add more property types and corresponding icons as needed
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
            // Load property image here
            Image(
                painter = rememberAsyncImagePainter(model = R.drawable.ic_default),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )

            // Rounded icon
            Icon(
                imageVector = propertyTypesIcons[property.propertyType] ?: Icons.Default.Home,
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier.size(40.dp).align(Alignment.BottomStart).padding(8.dp)
            )
            IconButton(
                onClick = { /*TODO*/ },
                modifier = Modifier.align(Alignment.TopEnd),
                colors = IconButtonDefaults.iconButtonColors(
                    contentColor = MaterialTheme.colorScheme.onPrimary,
                    containerColor = MaterialTheme.colorScheme.primary
                )
            ) {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(40.dp).padding(8.dp)
                )
            }
        }
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = property.propertyName,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Text(
                text = "${property.street} ${property.streetNumber}, ${property.city} ${property.zipCode}", // ktlint-disable max-line-length
                style = MaterialTheme.typography.bodySmall
            )
            Text(
                text = property.propertyType,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(top = 8.dp)
            )
            Button(
                onClick = {
                    Log.i("TEST", "PropertyCard:selected property: $property")
                    propertyViewModel.selectProperty(property)
                    navigateTo("PropertyDetail")
                },
                modifier = Modifier.fillMaxWidth().padding(top = 4.dp)
            ) {
                Text(text = "View Property")
            }
        }
    }
}
