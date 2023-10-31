import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text

import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import foliocontrol.android.foliocontrol_android.R
import foliocontrol.android.foliocontrolandroid.data.Property
import kotlin.random.Random


@Composable
fun PropertyCard(
    property: Property,

    navigateTo: (Any?) -> Unit = {}, modifier: Modifier = Modifier
) {
    val propertyTypesIcons: Map<String, ImageVector> = mapOf(
        "Type1" to Icons.Default.Home,
        "Type2" to Icons.Default.Home,
        "Type3" to Icons.Default.Home,
        "Type4" to Icons.Default.Home,
        // Add more property types and corresponding icons as needed
    )
    Card(

        modifier = Modifier.padding(8.dp), elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ), shape = MaterialTheme.shapes.small


        ,
        colors = CardDefaults.cardColors(

            contentColor = MaterialTheme.colorScheme.primary,
            containerColor = MaterialTheme.colorScheme.secondary
        )
    ) {
        Box(
            modifier = Modifier
                .height(150.dp)
                .fillMaxSize()
        ) {
            // Load property image here
            Image(
                painter = rememberImagePainter(data = R.drawable.ic_default),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )

            // Rounded icon
            Icon(
                imageVector = propertyTypesIcons[property.propertyType] ?: Icons.Default.Home,
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier
                    .size(40.dp)
                    .align(Alignment.BottomStart)
                    .padding(8.dp)
            )
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
                text = "${property.street} ${property.streetNumber}, ${property.city} ${property.zipCode}",
                style = MaterialTheme.typography.bodySmall,
            )
            Text(
                text = property.propertyType,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(top = 8.dp)
            )
            Button(
                onClick = { navigateTo(property.propertyID) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 4.dp),
            ) {
                Text(text = "View Property")
            }
        }
    }
}