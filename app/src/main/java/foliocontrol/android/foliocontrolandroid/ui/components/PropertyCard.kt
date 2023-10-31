package foliocontrol.android.foliocontrolandroid.components

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.google.accompanist.flowlayout.FlowRow
import com.google.accompanist.flowlayout.SizeMode
import foliocontrol.android.foliocontrolandroid.data.Property
import kotlin.random.Random

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PropertyCard(
    title: String,
    modifier: Modifier = Modifier,
    navigateTo: (Any?) -> Unit,
    property: Property
) {
    Card(
        modifier = modifier, colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        ), shape = MaterialTheme.shapes.medium
    ) {
        Image(
            painter = rememberAsyncImagePainter(
                model = "https://picsum.photos/seed/${Random.nextInt()}/300/200"
            ),
            contentDescription = null,
            modifier = Modifier
                .clip(MaterialTheme.shapes.large)
                .fillMaxSize()
                .aspectRatio(3f / 2f)

        )
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = title, style = MaterialTheme.typography.titleLarge
            )
            Spacer(modifier = Modifier.height(8.dp))
            FlowRow(
                modifier = Modifier.fillMaxSize(),
                mainAxisSize = SizeMode.Wrap,
                mainAxisSpacing = 8.dp

            ) {
                AssistChip(onClick = { Log.i("prop", "wa")
                    navigateTo(property.propertyID)}, colors = AssistChipDefaults.assistChipColors(
                    leadingIconContentColor = MaterialTheme.colorScheme.onSurfaceVariant
                ), leadingIcon = {
                    Icon(imageVector = Icons.Outlined.Edit, contentDescription = null)
                }, label = {
                    Text(text = "Edit")
                })

//
            }
        }
    }
}
