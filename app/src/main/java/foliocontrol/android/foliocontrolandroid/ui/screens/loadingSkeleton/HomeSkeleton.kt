package foliocontrol.android.foliocontrolandroid.ui.screens.loadingSkeleton

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import foliocontrol.android.foliocontrolandroid.screens.MultiFloatingState
import foliocontrol.android.foliocontrolandroid.ui.components.card.PropertyCardSkeleton
import foliocontrol.android.foliocontrolandroid.ui.components.foliocomponents.MultiFloatingButton

/**
 * Composable function representing the loading skeleton for the Home screen.
 * It displays a scaffold with a multi-floating action button and a lazy column containing property card skeletons.
 */
@Composable
fun HomeSkeleton() {
    Scaffold(floatingActionButton = {
        MultiFloatingButton(
            multiFloatingState = MultiFloatingState.Collapsed,
            onMultiFabStateChange = {
                MultiFloatingState.Collapsed
            },
            toggleAddPropertyDialog = {
                // Do nothing
            },
            items = listOf(),
            toggleSearchBar = {
                // Do nothing
            },
        )
    }) { values ->
        Column {
            LazyColumn(contentPadding = values) {
                items(3) { property ->
                    PropertyCardSkeleton()
                }
            }
        }
    }
}
