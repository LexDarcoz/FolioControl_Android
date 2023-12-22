package foliocontrol.android.foliocontrolandroid.ui.screens.loadingSkeleton

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.runtime.Composable
import foliocontrol.android.foliocontrolandroid.screens.MultiFloatingState
import foliocontrol.android.foliocontrolandroid.ui.components.card.PropertyCard
import foliocontrol.android.foliocontrolandroid.ui.components.card.PropertyCardSkeleton
import foliocontrol.android.foliocontrolandroid.ui.components.dialogs.items
import foliocontrol.android.foliocontrolandroid.ui.components.foliocomponents.MultiFloatingButton
import foliocontrol.android.foliocontrolandroid.ui.components.foliocomponents.SearchBar

@Composable
fun HomeSkeleton() {

    Scaffold(floatingActionButton = {
        MultiFloatingButton(multiFloatingState = MultiFloatingState.Collapsed,
            onMultiFabStateChange = {
                MultiFloatingState.Collapsed
            },

            toggleAddPropertyDialog = {

            },
            items = listOf(),
            toggleSearchBar = {

            })

    }) { values ->
        Column {
            LazyColumn(contentPadding = values) {
                items(3) { property ->
                    PropertyCardSkeleton(


                    )
                }
            }

        }

    }
}