package foliocontrol.android.foliocontrolandroid.ui.screens.portfolio

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddHomeWork
import androidx.compose.material.icons.filled.WifiOff
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import foliocontrol.android.foliocontrolandroid.domain.Premise
import foliocontrol.android.foliocontrolandroid.ui.components.OfflineScreen
import foliocontrol.android.foliocontrolandroid.ui.components.card.PremiseCard
import foliocontrol.android.foliocontrolandroid.ui.viewModels.common.EmptyListScreen
import foliocontrol.android.foliocontrolandroid.ui.viewModels.common.WindowInfo

/**
 * Composable function representing the screen for displaying a list of premises associated with a property.
 *
 * @param propertyPremises List of premises to be displayed.
 * @param windowInfo Information about the window dimensions and layout type.
 * @param offline Boolean flag indicating whether the app is in offline mode.
 */
@Composable
fun PremisesListScreen(
    propertyPremises: List<Premise>,
    windowInfo: WindowInfo,
    offline: Boolean = false,
) {
    Box(
        modifier =
            Modifier
                .fillMaxSize()
                .padding(
                    if (windowInfo.screenWidthInfo == WindowInfo.WindowType.Compact) {
                        16.dp
                    } else {
                        4.dp
                    },
                ),
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
        ) {
            Text(
                text = "Premises:",
                style =
                    if (windowInfo.screenWidthInfo == WindowInfo.WindowType.Compact) {
                        MaterialTheme.typography.titleLarge
                    } else {
                        MaterialTheme.typography.bodySmall
                    },
                fontWeight = FontWeight.Bold,
                modifier =
                    if (windowInfo.screenWidthInfo == WindowInfo.WindowType.Compact) {
                        Modifier.padding(8.dp)
                    } else {
                        Modifier.padding(4.dp)
                    },
            )

            when {
                offline -> OfflineScreen()
                propertyPremises.isEmpty() ->
                    EmptyListScreen(
                        "No premises available.",
                    )

                else -> PremisesList(propertyPremises)
            }
            Button(
                enabled = offline,
                onClick = {
                    // TODO
                },
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp),
            ) {
                if (offline) {
                    Row {
                        Icon(
                            Icons.Default.WifiOff,
                            contentDescription = null,
                            modifier = Modifier.padding(end = 8.dp),
                        )
                        Text(text = "Offline preview")
                    }
                } else {
                    Row {
                        Icon(
                            Icons.Default.AddHomeWork,
                            contentDescription = null,
                            modifier = Modifier.padding(end = 8.dp),
                        )
                        Text(text = "Add premise")
                    }
                }
            }
        }
    }
}

/**
 * Composable function representing a list of premises.
 *
 * @param propertyPremises List of premises to be displayed.
 */
@Composable
fun PremisesList(propertyPremises: List<Premise>) {
    LazyColumn(
        modifier = Modifier,
    ) {
        items(propertyPremises) { premise ->
            PremiseCard(premise)
        }
    }
}
