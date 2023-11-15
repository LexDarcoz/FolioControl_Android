import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import foliocontrol.android.foliocontrol_android.R
import foliocontrol.android.foliocontrolandroid.ui.viewModels.PropertyViewModel

@Composable
fun PropertyDetailScreen(
    propertyViewModel: PropertyViewModel,
    navigateTo: (Any?) -> Unit = {}
) {
    Box(
        modifier = Modifier.fillMaxSize()

    ) {
        Column(
            modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState())
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_default), // Replace with your image resource
                contentDescription = "Property Image",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxWidth().height(200.dp)
            )

            Surface(
                color = MaterialTheme.colorScheme.secondary,
                contentColor = MaterialTheme.colorScheme.primary,
                shape = MaterialTheme.shapes.medium,
                shadowElevation = 4.dp,
                modifier = Modifier.fillMaxWidth().padding(16.dp)

            ) {
                Column(
                    modifier = Modifier.fillMaxSize().padding(16.dp)
                ) {
                    Text(
                        text = "Details - ${propertyViewModel.propertyState.propertyName}",
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )
                    PropertyField("Name", propertyViewModel.propertyState.propertyName) {
                        propertyViewModel.handlePropertyEdit(
                            propertyName = it
                        )
                    }
                    PropertyField("Type", propertyViewModel.propertyState.propertyType) {
                        propertyViewModel.handlePropertyEdit(
                            propertyType = it
                        )
                    }
                    PropertyField("Street", propertyViewModel.propertyState.street) {
                        propertyViewModel.handlePropertyEdit(street = it)
                    }
                    PropertyField("Street Number", propertyViewModel.propertyState.streetNumber) {
                        propertyViewModel.handlePropertyEdit(streetNumber = it)
                    }
                    PropertyField("Zip Code", propertyViewModel.propertyState.zipCode) {
                        propertyViewModel.handlePropertyEdit(
                            zipCode = it
                        )
                    }
                    PropertyField("City", propertyViewModel.propertyState.city) {
                        propertyViewModel.handlePropertyEdit(city = it)
                    }
                    PropertyField("Country", propertyViewModel.propertyState.country) {
                        propertyViewModel.handlePropertyEdit(
                            country = it
                        )
                    }
                    Spacer(modifier = Modifier.weight(1f))
                    Button(
                        onClick = {
                            propertyViewModel.handlePropertySave()
                            navigateTo("Home")
                        },
                        modifier = Modifier.fillMaxWidth().padding(vertical = 16.dp)
                    ) {
                        Text(text = "Save")
                    }
                }
            }
        }
    }
}

@Composable
fun PropertyField(label: String, value: String, onValueChange: (String) -> Unit) {
    Column {
        Text(text = label, style = MaterialTheme.typography.bodyLarge)
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = androidx.compose.ui.text.input.ImeAction.Done
            ),
            keyboardActions = KeyboardActions(onDone = {
                // Handle done action
            }),
            textStyle = MaterialTheme.typography.bodySmall,
            singleLine = true,
            modifier = Modifier.fillMaxWidth().background(MaterialTheme.colorScheme.secondary)
                .height(50.dp)
        )
    }
}
