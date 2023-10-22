
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun TableCell(
    text: String,
    weight: Float
) {
    Text(
        text = text,
        modifier = Modifier
            .border(1.dp, Color.Black)
            .padding(8.dp)
    )
}

data class PartnershipData(
    val name: String,
    val vatNumber: String,
    val address: String,
    val country: String
)

@Composable
fun PartnershipTableScreen() {
    val tableData = (1..10).mapIndexed { index, _ ->
        PartnershipData(
            "Partnership $index",
            "VAT$index",
            "Address $index",
            "Country $index"
        )
    }

    // Each cell of a column must have the same weight.
    val column1Weight = .3f // 30%
    val column2Weight = .7f // 70%

    // The LazyColumn will be our table. Notice the use of the weights below
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        // Here is the header
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                TableCell(text = "Partnership name", weight = column1Weight)
                TableCell(text = "VAT number", weight = column2Weight)
                TableCell(text = "Address", weight = column2Weight)
                TableCell(text = "Country", weight = column2Weight)
                TableCell(text = "Options", weight = column2Weight)
            }
        }
        // Here are all the rows of your table.
        items(tableData) { partnershipData ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                TableCell(text = partnershipData.name, weight = column1Weight)
                TableCell(text = partnershipData.vatNumber, weight = column2Weight)
                TableCell(text = partnershipData.address, weight = column2Weight)
                TableCell(text = partnershipData.country, weight = column2Weight)
                TableCell(text = "Edit/Delete", weight = column2Weight)
            }
        }
    }
}
