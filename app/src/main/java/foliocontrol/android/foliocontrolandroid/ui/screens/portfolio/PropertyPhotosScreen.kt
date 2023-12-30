package foliocontrol.android.foliocontrolandroid.ui.screens.portfolio

import android.content.Context
import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Image
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Upload
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material.icons.filled.WifiOff
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import foliocontrol.android.foliocontrolandroid.data.remote.common.Constants
import foliocontrol.android.foliocontrolandroid.domain.Property
import foliocontrol.android.foliocontrolandroid.ui.components.dialogs.DeleteDialog
import foliocontrol.android.foliocontrolandroid.ui.components.dialogs.ImageDialog
import foliocontrol.android.foliocontrolandroid.ui.viewModels.common.UiState
import foliocontrol.android.foliocontrolandroid.ui.viewModels.common.WindowInfo

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun PropertyPhotosScreen(
    //GET DATA
    getDataForActiveProperty: () -> Unit,
    //IMAGE FUNCTIONS
    clearImage: () -> Unit, uploadImage: (Context, Uri?) -> Unit,
    //Property state
    propertyState: Property,
    //EDITTING


    //UISTATE
    uiState: UiState,


    windowInfo: WindowInfo, offline: Boolean = false
) {
    val imageUrl = Constants.PROPERTYPHOTOS_URL
    val defaultImage = "$imageUrl/default.avif"
    val propertyImg = propertyState.propertyImg.ifEmpty { "null" }
    val openDeleteDialog = remember { mutableStateOf(false) }
    var imageFullScreen by remember { mutableStateOf(false) }
    //get the context
    val context = LocalContext.current
    val image = when (propertyImg) {
        "null" -> defaultImage
        else -> "$imageUrl/$propertyImg"
    }

    var selectedImageUri by remember {
        mutableStateOf<Uri?>(null)
    }

    val singlePhotoPickerLauncher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.PickVisualMedia(),
            onResult = { uri ->
                selectedImageUri = uri
            })
    when {
        openDeleteDialog.value -> {
            if (uiState is UiState.Success) {
                if (selectedImageUri != null) {
                    DeleteDialog(
                        onDismissRequest = { openDeleteDialog.value = false },
                        onConfirmation = {
                            openDeleteDialog.value = false
                            selectedImageUri = null
                        },
                        confirmText = "Confirm",
                        dismissText = "Dismiss",
                        dialogTitle = "Clear Image",
                        dialogText = "Are you sure you want to delete this image?",
                        icon = Icons.Default.Warning
                    )
                } else {
                    DeleteDialog(
                        onDismissRequest = { openDeleteDialog.value = false },
                        onConfirmation = {
                            clearImage()
                            openDeleteDialog.value = false
                        },
                        confirmText = "Confirm",
                        dismissText = "Dismiss",
                        dialogTitle = "Clear Image",
                        dialogText = "Are you sure you want to revert to default?",
                        icon = Icons.Default.Warning
                    )
                }
            } else {
                DeleteDialog(
                    onDismissRequest = { openDeleteDialog.value = false },
                    onConfirmation = {
                        openDeleteDialog.value = false
                        getDataForActiveProperty()
                    },
                    confirmText = "Retry Connection",
                    dismissText = "Dismiss",
                    dialogTitle = "Network Error",
                    dialogText = "You need to be online in order to delete images.",
                    icon = Icons.Default.Info
                )
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                if (windowInfo.screenWidthInfo == WindowInfo.WindowType.Compact) {
                    16.dp
                } else {
                    4.dp
                }
            )
    ) {
        Column(
            modifier = Modifier.fillMaxSize()


        ) {
            Text(text = "Photos:",
                style = windowInfo.screenWidthInfo.let { windowType ->
                    if (windowType == WindowInfo.WindowType.Compact) MaterialTheme.typography.titleLarge
                    else MaterialTheme.typography.bodySmall
                },
                fontWeight = FontWeight.Bold,
                modifier = windowInfo.screenWidthInfo.let { windowType ->
                    if (windowType == WindowInfo.WindowType.Compact) Modifier.padding(8.dp)
                    else Modifier.padding(4.dp)
                })

            Card(

                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.8f)
                    .padding(
                        top = if (windowInfo.screenWidthInfo == WindowInfo.WindowType.Compact) {
                            32.dp
                        } else {
                            8.dp
                        }
                    ), elevation = CardDefaults.cardElevation(
                    defaultElevation = 6.dp
                ), shape = MaterialTheme.shapes.small, colors = CardDefaults.cardColors(
                    contentColor = MaterialTheme.colorScheme.primary,
                    containerColor = MaterialTheme.colorScheme.secondary
                )

            ) {
                Box {
                    if (selectedImageUri != null) {
                        GlideImage(model = selectedImageUri,
                            contentDescription = "Property Image",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .fillMaxSize()
                                .clickable {
                                    imageFullScreen = true

                                }

                        )
                    } else {
                        GlideImage(model = image,
                            contentDescription = "Property Image",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .fillMaxSize()
                                .width(100.dp)
                                .clickable {
                                    imageFullScreen = true
                                })
                    }
                    Icon(
                        imageVector = Constants.propertyTypesIcons[propertyState.propertyType]
                            ?: Icons.Default.Home,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.secondary,
                        modifier = Modifier
                            .size(40.dp)
                            .align(Alignment.BottomStart)
                            .padding(8.dp)
                    )
                    IconButton(
                        onClick = { openDeleteDialog.value = true },
                        modifier = Modifier.align(Alignment.TopEnd),
                        colors = IconButtonDefaults.iconButtonColors(
                            contentColor = MaterialTheme.colorScheme.onPrimary,
                            containerColor = MaterialTheme.colorScheme.primary
                        )
                    ) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.onPrimary,
                            modifier = Modifier
                                .size(40.dp)
                                .padding(8.dp)
                        )
                    }
                }
            }

            Button(
                enabled = !offline, onClick = {

                    if (selectedImageUri != null) {
                        uploadImage(context, selectedImageUri!!)
                        selectedImageUri = null
                    } else {
                        singlePhotoPickerLauncher.launch(
                            PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                        )
                    }

                    Log.i("TEST", "PropertyPhotosScreen: ${selectedImageUri.toString()}")

                }, modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp)
            ) {
                if (offline) {
                    Row {
                        Icon(
                            Icons.Default.WifiOff,
                            contentDescription = null,
                            modifier = Modifier.padding(end = 8.dp)
                        )
                        Text(text = "Offline preview")
                    }
                } else {
                    if (selectedImageUri != null) {
                        Row {
                            Icon(
                                Icons.Default.Upload,
                                contentDescription = null,
                                modifier = Modifier.padding(end = 8.dp)
                            )
                            Text(text = "Upload Image")
                        }
                    } else {
                        Row {
                            Icon(
                                Icons.Default.Image,
                                contentDescription = null,
                                modifier = Modifier.padding(end = 8.dp)
                            )
                            Text(text = "Select Image")
                        }
                    }
                }
            }
        }
    }

    when {
        imageFullScreen -> {
            ImageDialog(
                onDismissRequest = {
                    imageFullScreen = false
                }, imageUrl = if (selectedImageUri != null) {
                    selectedImageUri.toString()
                } else {
                    image
                }
            )
        }
    }
}
