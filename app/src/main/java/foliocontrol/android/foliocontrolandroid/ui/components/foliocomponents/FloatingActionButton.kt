package foliocontrol.android.foliocontrolandroid.ui.components.foliocomponents

import android.annotation.SuppressLint
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import foliocontrol.android.foliocontrolandroid.screens.Identifier
import foliocontrol.android.foliocontrolandroid.screens.MinFabItem
import foliocontrol.android.foliocontrolandroid.screens.MultiFloatingState
import kotlin.math.roundToInt

/**
 * Composable function representing a multi-floating action button with expandable options.
 *
 * @param multiFloatingState The state of the multi-floating action button, either [MultiFloatingState.Collapsed] or [MultiFloatingState.Expanded].
 * @param onMultiFabStateChange Callback function to handle changes in the multi-floating action button state.
 * @param items List of [MinFabItem] representing the individual options of the multi-floating action button.
 * @param toggleAddPropertyDialog Callback function to toggle the visibility of the add property dialog.
 * @param toggleSearchBar Callback function to toggle the visibility of the search bar.
 */
@Composable
fun MultiFloatingButton(
    multiFloatingState: MultiFloatingState,
    onMultiFabStateChange: (MultiFloatingState) -> Unit,
    items: List<MinFabItem>,
    toggleAddPropertyDialog: () -> Unit,
    toggleSearchBar: () -> Unit,
) {
    var offsetY by remember {
        mutableFloatStateOf(0f)
    }
    val configuration = LocalConfiguration.current
    val transition = updateTransition(targetState = multiFloatingState, label = "transition")
    val rotate by transition.animateFloat(label = "rotate") {
        if (it == MultiFloatingState.Expanded) 315f else 0f
    }
    val alpha by transition.animateFloat(
        label = "alpha",
        transitionSpec = { tween(durationMillis = 100) },
    ) {
        if (it == MultiFloatingState.Expanded) 1f else 0f
    }

    fun toggleFab() {
        onMultiFabStateChange(
            if (transition.currentState == MultiFloatingState.Expanded) {
                MultiFloatingState.Collapsed
            } else {
                MultiFloatingState.Expanded
            },
        )
    }

    Column(
        horizontalAlignment = Alignment.End,
    ) {
        if (transition.currentState == MultiFloatingState.Expanded) {
            Card(
                shape = RoundedCornerShape(16.dp),
                colors =
                    CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.onTertiary,
                    ),
                modifier =
                    Modifier
                        .offset { IntOffset(0, offsetY.roundToInt()) }
                        .padding(bottom = 16.dp)
                        .alpha(
                            animateFloatAsState(
                                targetValue = alpha,
                                animationSpec = tween(100),
                                label = "",
                            ).value,
                        )
                        .width(190.dp),
                elevation = CardDefaults.cardElevation(8.dp),
            ) {
                items.forEach {
                    MinFab(
                        item = it,
                        onMinFabItemClick = { minFabItem ->
                            when (minFabItem.identifier) {
                                Identifier.SearchFab.name -> {
                                    toggleSearchBar()
                                    toggleFab()
                                }

                                Identifier.AddPropertyFab.name -> {
                                    toggleAddPropertyDialog()
                                    toggleFab()
                                }
                            }
                        },
                        alpha = alpha,
                    )
                    if (it != items.last()) {
                        Divider(
                            modifier =
                                Modifier
                                    .fillMaxWidth()
                                    .height(1.dp),
                            color = MaterialTheme.colorScheme.primary,
                        )
                    }
                }
            }
        }

        FloatingActionButton(
            modifier =
                Modifier
                    .height(70.dp)
                    .width(70.dp)
                    .offset { IntOffset(0, offsetY.roundToInt()) }
                    .draggable(
                        orientation = Orientation.Vertical,
                        state =
                            rememberDraggableState { delta ->
                                if (offsetY + delta < 0 && offsetY + delta > -configuration.screenHeightDp) {
                                    offsetY += delta
                                }
                            },
                    ),
            onClick = {
                toggleFab()
            },
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = null,
                modifier =
                    Modifier
                        .rotate(rotate)
                        .fillMaxSize(0.6f),
            )
        }
    }
}

/**
 * Composable function representing a mini-floating action button.
 *
 * @param item The [MinFabItem] representing the individual option.
 * @param alpha The alpha value to control the visibility of the mini-floating action button.
 * @param showLabel Boolean indicating whether to show the label text.
 * @param onMinFabItemClick Callback function invoked when the mini-floating action button is clicked.
 */
@SuppressLint("UnrememberedMutableInteractionSource")
@Composable
fun MinFab(
    item: MinFabItem,
    alpha: Float,
    showLabel: Boolean = true,
    onMinFabItemClick: (MinFabItem) -> Unit,
) {
    Row(
        modifier =
            Modifier
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    onClick = {
                        onMinFabItemClick.invoke(item)
                    },
                    indication =
                        rememberRipple(
                            bounded = true,
                            radius = 252.dp,
                            color = MaterialTheme.colorScheme.surface,
                        ),
                )
                .alpha(
                    animateFloatAsState(
                        targetValue = alpha,
                        animationSpec = tween(100),
                        label = "",
                    ).value,
                ),
    ) {
        Row(
            modifier =
                Modifier
                    .padding(10.dp)
                    .fillMaxWidth(),
        ) {
            Icon(
                imageVector = item.icon,
                contentDescription = null,
                modifier =
                    Modifier
                        .size(32.dp)
                        .fillMaxSize()
                        .alpha(alpha),
            )

            if (showLabel) {
                Text(
                    text = item.label,
                    color = MaterialTheme.colorScheme.primary,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    modifier =
                        Modifier
                            .alpha(alpha)
                            .padding(start = 12.dp)
                            .fillMaxWidth(),
                )
            }
        }
    }
}
