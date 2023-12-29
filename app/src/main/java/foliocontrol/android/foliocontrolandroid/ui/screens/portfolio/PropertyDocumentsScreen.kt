package foliocontrol.android.foliocontrolandroid.ui.screens.portfolio

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.OpenableColumns
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Apartment
import androidx.compose.material.icons.filled.AttachMoney
import androidx.compose.material.icons.filled.FileCopy
import androidx.compose.material.icons.filled.FileUpload
import androidx.compose.material.icons.filled.Garage
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.House
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Key
import androidx.compose.material.icons.filled.Money
import androidx.compose.material.icons.filled.MoneyOff
import androidx.compose.material.icons.filled.Store
import androidx.compose.material.icons.filled.Villa
import androidx.compose.material.icons.filled.Warehouse
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material.icons.filled.WifiOff
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerColors
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.maxkeppeker.sheets.core.models.base.rememberSheetState
import com.maxkeppeler.sheets.calendar.CalendarDialog
import com.maxkeppeler.sheets.calendar.models.CalendarSelection
import foliocontrol.android.foliocontrolandroid.domain.Property
import foliocontrol.android.foliocontrolandroid.domain.PropertyDocument
import foliocontrol.android.foliocontrolandroid.ui.components.OfflineScreen
import foliocontrol.android.foliocontrolandroid.ui.components.card.DocumentCard
import foliocontrol.android.foliocontrolandroid.ui.components.dialogs.DeleteDialog
import foliocontrol.android.foliocontrolandroid.ui.components.dialogs.items
import foliocontrol.android.foliocontrolandroid.ui.components.foliocomponents.DropDownMenuItem
import foliocontrol.android.foliocontrolandroid.ui.components.foliocomponents.FolioDropdown
import foliocontrol.android.foliocontrolandroid.ui.components.foliocomponents.FolioTextField
import foliocontrol.android.foliocontrolandroid.ui.viewModels.PropertyViewModel
import foliocontrol.android.foliocontrolandroid.ui.viewModels.common.EmptyListScreen
import foliocontrol.android.foliocontrolandroid.ui.viewModels.common.UiState
import foliocontrol.android.foliocontrolandroid.ui.viewModels.common.WindowInfo
import java.util.Calendar


val documentTypes = listOf(
    DropDownMenuItem(
        "AKTE", {
            Icon(
                Icons.Default.FileCopy,
                contentDescription = "Akte",
                tint = MaterialTheme.colorScheme.onSurface
            )
        }, "Apartment"
    ), DropDownMenuItem(
        "JAARLIJKSE AFREKENING", {
            Icon(
                Icons.Default.MoneyOff,
                contentDescription = "Jaarlijkse afrekening",
                tint = MaterialTheme.colorScheme.onSurface
            )
        }, "Jaarlijkse afrekening"
    ), DropDownMenuItem(
        "AFSCHRIFT", {
            Icon(
                Icons.Default.AttachMoney,
                contentDescription = "Afschrift",
                tint = MaterialTheme.colorScheme.onSurface
            )
        }, "Afschrift"
    ), DropDownMenuItem(
        "EIGENDOMSAKTE", {
            Icon(
                Icons.Default.Key,
                contentDescription = "Eigendomsakte",
                tint = MaterialTheme.colorScheme.onSurface
            )
        }, "Eigendomsakte"
    ), DropDownMenuItem(
        "UITTREKSEL", {
            Icon(
                Icons.Default.Money,
                contentDescription = "Uittrekksel",
                tint = MaterialTheme.colorScheme.onSurface
            )
        }, "Uittrekksel"
    ), DropDownMenuItem(
        "BODEMATTEST", {
            Icon(
                Icons.Default.House,
                contentDescription = "Bodem attest",
                tint = MaterialTheme.colorScheme.onSurface
            )
        }, "Bodem attest"
    )
)


@Composable
fun PropertyDocumentsScreen(
    //UI state
    uiState: UiState,

    //document
    propertyDocuments: List<PropertyDocument>,


    //data
    getDataForActiveProperty: () -> Unit,

    //Document functions
    handleDocumentDelete: (Int) -> Unit,
    downloadFile: (String) -> Long,
    //handle document edit
    handleAddDocumentDateEdit: (String) -> Unit,
    handleAddDocumentTypeEdit: (String) -> Unit,
    propertyDocumentState: PropertyDocument,
    uploadDocument: (Context, Uri) -> Unit,
    windowInfo: WindowInfo,
    offline: Boolean = false
) {
    val pdfMimeType = "application/pdf"
    var selectedDocumentUri by remember {
        mutableStateOf<Uri?>(null)
    }
    val singleDocumentLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.OpenDocument(),
        onResult = { uri ->
            selectedDocumentUri = uri
            Log.i("TEST", "PropertyDocumentsScreen: ${selectedDocumentUri.toString()}")
        })
    Log.i("TEST", "PropertyDocumentsScreen: ${selectedDocumentUri.toString()}")


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
            modifier = Modifier.fillMaxSize(

            )


        ) {
            Text(text = "Documents:",
                style = windowInfo.screenWidthInfo.let { windowType ->
                    if (windowType == WindowInfo.WindowType.Compact) MaterialTheme.typography.titleLarge
                    else MaterialTheme.typography.bodySmall
                },
                fontWeight = FontWeight.Bold,
                modifier = windowInfo.screenWidthInfo.let { windowType ->
                    if (windowType == WindowInfo.WindowType.Compact) Modifier.padding(8.dp)
                    else Modifier.padding(4.dp)
                }

            )

            when {
                offline -> OfflineScreen()
                selectedDocumentUri != null -> AddDocument(
                    selectedDocumentUri,
                    handleAddDocumentDateEdit,
                    handleAddDocumentTypeEdit,
                    propertyDocumentState,
                    uploadDocument,

                    ) {
                    selectedDocumentUri = it
                }

                propertyDocuments.isEmpty() -> EmptyListScreen(
                    "No documents available."
                )


                else -> {
                    DocumentsList(
                        propertyDocuments,
                        uiState,
                        getDataForActiveProperty,
                        downloadFile,
                        handleDocumentDelete,
                        windowInfo
                    )


                }
            }

            // Upload Button
            Button(
                enabled = !offline, onClick = {
                    if (selectedDocumentUri == null) {
                        singleDocumentLauncher.launch(arrayOf(pdfMimeType))
                    }
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
                    Row {
                        Icon(
                            Icons.Default.FileUpload,
                            contentDescription = null,
                            modifier = Modifier.padding(end = 8.dp)
                        )
                        Text(text = "Upload document")
                    }
                }
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddDocument(
    selectedDocumentUri: Uri? = null,
    handleAddDocumentDateEdit: (String) -> Unit,
    handleAddDocumentTypeEdit: (String) -> Unit,
    propertyDocumentState: PropertyDocument,
    uploadDocument: (Context, Uri) -> Unit,
    changeSelectedDocumentUri: (Uri?) -> Unit
) {
    val context = LocalContext.current
    fun getDocumentName(uri: Uri): String {
        val cursor = context?.contentResolver?.query(uri, null, null, null, null)
        val nameIndex = cursor?.getColumnIndex(OpenableColumns.DISPLAY_NAME)
        cursor?.moveToFirst()
        val name = cursor?.getString(nameIndex ?: -1) ?: "Unknown Document"
        cursor?.close()
        return name
    }


    val documentName = getDocumentName(selectedDocumentUri!!)
    val calendarState = rememberSheetState()
    val documentTypeState = remember { mutableStateOf<String?>(null) }
    var expanded by remember { mutableStateOf(false) }


    CalendarDialog(
        state = calendarState,
        selection = CalendarSelection.Date {
            handleAddDocumentDateEdit(
                it.toString()
            )
        },
    )
    // UI Components
    Column(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxSize()
    ) {
        Surface(
            color = MaterialTheme.colorScheme.onPrimary,
            contentColor = MaterialTheme.colorScheme.onSurface,
            shape = MaterialTheme.shapes.large,
            modifier = Modifier
                .fillMaxWidth()
                .clip(MaterialTheme.shapes.medium)
                .padding(16.dp)
                .height(500.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp)
                ) {
                    Text(
                        "Selected Document: $documentName",
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface,

                        )
                }
                // Date Selection Text Field
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp)
                ) {
                    Text(
                        text = "Enter expiry date",
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onSurface
                    )

                    OutlinedTextField(enabled = false,
                        value = "Expiry Date: ${
                            propertyDocumentState.expiryDate ?: "Select Date"
                        }",
                        onValueChange = {},
                        keyboardOptions = KeyboardOptions.Default.copy(
                            imeAction = ImeAction.Done
                        ),
                        keyboardActions = KeyboardActions(onDone = {
                            // Handle done action
                        }),
                        textStyle = MaterialTheme.typography.bodySmall.copy(
                            color = MaterialTheme.colorScheme.onSurface
                        ),
                        singleLine = true,
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(MaterialTheme.colorScheme.secondary)
                            .height(50.dp)
                            .clickable { calendarState.show() })
                }


                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp)
                ) {
                    // Document Type Dropdown
                    FolioDropdown(
                        expanded = expanded,
                        toggleExpanded = { expanded = !expanded },
                        items = documentTypes,
                        label = "Type",
                        onItemSelect = { selectedItem ->
                            documentTypeState.value = selectedItem
                            handleAddDocumentTypeEdit(
                                selectedItem
                            )
                        },

                        )
                }
                // Buttons
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Button(modifier = Modifier
                        .weight(1f)
                        .padding(end = 8.dp),
                        enabled = propertyDocumentState.expiryDate.isNotBlank() && propertyDocumentState.documentType.isNotBlank(),
                        onClick = {
                            uploadDocument(
                                context, selectedDocumentUri
                            )
                            changeSelectedDocumentUri(null)
                        }) {
                        Text("Submit")
                    }

                    Button(modifier = Modifier
                        .weight(1f)
                        .padding(start = 8.dp),
                        onClick = { changeSelectedDocumentUri(null) }) {
                        Text("Clear")
                    }
                }
            }
        }
    }
}


@Composable
fun DocumentsList(
    propertyDocuments: List<PropertyDocument>,
    uiState: UiState,
    getDataForActiveProperty: () -> Unit,
    downloadFile: (String) -> Long,
    handleDocumentDelete: (Int) -> Unit,
    windowInfo: WindowInfo
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.8f)
            .padding(
                top = if (windowInfo.screenWidthInfo == WindowInfo.WindowType.Compact) {
                    32.dp
                } else {
                    8.dp
                }
            )
    ) {
        val openDeleteDialog = remember { mutableStateOf(false) }
        var documentID by remember { mutableStateOf(0) }




        when {
            openDeleteDialog.value -> {
                if (uiState is UiState.Success) {
                    DeleteDialog(onDismissRequest = { openDeleteDialog.value = false },
                        onConfirmation = {
                            handleDocumentDelete(documentID)
                            openDeleteDialog.value = false

                        },
                        confirmText = "Delete",
                        dismissText = "Dismiss",
                        dialogTitle = "Delete document",
                        dialogText = "Are you sure you want to delete this document?",
                        icon = Icons.Default.Warning
                    )
                } else {
                    DeleteDialog(onDismissRequest = { openDeleteDialog.value = false },
                        onConfirmation = {
                            openDeleteDialog.value = false
                            getDataForActiveProperty()
                        },
                        confirmText = "Retry Connection",
                        dismissText = "Dismiss",
                        dialogTitle = "Network Error",
                        dialogText = "You need to be online in order to delete documents.",
                        icon = Icons.Default.Info
                    )
                }
            }
        }


        LazyColumn {
            items(items = propertyDocuments,
                key = { propertyDocument -> propertyDocument.propertyDocumentID }) { document ->
                DocumentCard(document, downloadFile) {
                    documentID = it
                    openDeleteDialog.value = !openDeleteDialog.value
                }
            }
        }
    }
}
