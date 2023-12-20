package foliocontrol.android.foliocontrolandroid.ui.components.card

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FileCopy
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import foliocontrol.android.foliocontrolandroid.data.remote.common.Constants.PROPERTYDOCUMENTS_URL
import foliocontrol.android.foliocontrolandroid.domain.PropertyDocument
import foliocontrol.android.foliocontrolandroid.ui.viewModels.PropertyViewModel

@Composable
fun DocumentCard(document: PropertyDocument, propertyViewModel: PropertyViewModel) {
    Card(
        modifier = Modifier.padding(8.dp).clickable {
            propertyViewModel.downloadFile(
                "$PROPERTYDOCUMENTS_URL/${document.documentName}"
            ) > 0
        },
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        shape = MaterialTheme.shapes.small,
        colors = CardDefaults.cardColors(
            contentColor = MaterialTheme.colorScheme.primary,
            containerColor = MaterialTheme.colorScheme.secondary
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth().padding(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.FileCopy,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary
                )

                Spacer(modifier = Modifier.width(8.dp))

                Text(
                    text = document.name,
                    maxLines = 2,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    overflow = TextOverflow.Ellipsis
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Type: ${document.documentType}",
                color = Color.Gray,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            Text(
                text = "Expiry Date: ${document.expiryDate}",
                color = Color.Gray,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}
