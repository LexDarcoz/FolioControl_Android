package foliocontrol.android.foliocontrolandroid.ui.components.card

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun PropertyCardSkeleton() {
    Card(
        modifier = Modifier.padding(8.dp),
        elevation =
            CardDefaults.cardElevation(
                defaultElevation = 6.dp,
            ),
        shape = MaterialTheme.shapes.small,
        colors =
            CardDefaults.cardColors(
                contentColor = MaterialTheme.colorScheme.primary,
                containerColor = MaterialTheme.colorScheme.secondary,
            ),
    ) {
        Box(
            modifier =
                Modifier
                    .height(165.dp)
                    .fillMaxSize(),
        ) {
            Shimmer(
                modifier =
                    Modifier
                        .fillMaxSize()
                        .width(200.dp),
            )
            Shimmer(
                modifier =
                    Modifier
                        .size(40.dp)
                        .align(Alignment.BottomStart)
                        .padding(8.dp),
            )
            Shimmer(
                modifier = Modifier.align(Alignment.TopEnd),
            ) {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onPrimary,
                    modifier =
                        Modifier
                            .size(40.dp)
                            .padding(8.dp),
                )
            }
        }
        Column(
            modifier = Modifier.padding(16.dp),
        ) {
            Shimmer(
                modifier =
                    Modifier
                        .padding(bottom = 8.dp)
                        .fillMaxWidth()
                        .height(24.dp),
            )
            Shimmer(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .height(16.dp),
            )
            Shimmer(
                modifier =
                    Modifier
                        .padding(top = 8.dp)
                        .fillMaxWidth()
                        .height(16.dp),
            )
            Shimmer(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .padding(top = 4.dp)
                        .height(48.dp),
            )
        }
    }
}

@Composable
fun Shimmer(
    modifier: Modifier = Modifier,
    content: @Composable (modifier: Modifier) -> Unit = {},
) {
    Box(
        modifier = modifier.background(MaterialTheme.colorScheme.primary.copy(alpha = 0.12f)),
    ) {
        content(Modifier.background(MaterialTheme.colorScheme.primary.copy(alpha = 0.24f)))
    }
}
