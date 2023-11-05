package foliocontrol.android.foliocontrolandroid.screens

import PropertyCard
import android.util.Log
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import foliocontrol.android.foliocontrolandroid.data.Property
import foliocontrol.android.foliocontrolandroid.viewModels.AuthViewModel
import foliocontrol.android.foliocontrolandroid.viewModels.PropertyViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(authViewModel: AuthViewModel, propertyViewModel: PropertyViewModel,navigateTo: (Any?) -> Unit = {}) {




    val properties = remember {
        mutableStateListOf(
            Property(
                0,
                "Kot Gent",
                "Apartment",
                "Aaaaa",
                "Voskenslaan",
                "4",
                "Gent",
                "9000",
                "Belgium",
                "qhubdqzibdqiyz",
                1
            ),
            Property(
                1,
                "Kot Gentzzz",
                "Apartment",
                "Aaaaa",
                "Voskenslaan",
                "4",
                "Gent",
                "9000",
                "Belgium",
                "qhubdqzibdqiyz",
                1
            ),
            Property(
                2,
                "Kot Gentaaa",
                "Apartment",
                "Aaaaa",
                "Voskenslaan",
                "4",
                "Gent",
                "9000",
                "Belgium",
                "qhubdqzibdqiyz",
                1
            ),
            Property(
                3,
                "Kot Gentaaa",
                "Apartment",
                "Aaaaa",
                "Voskenslaan",
                "4",
                "Gent",
                "9000",
                "Belgium",
                "qhubdqzibdqiyz",
                1
            )
        )
    }




    var token by rememberSaveable {
        mutableStateOf("")
    }

    fun handleClick() {
        if (token.isNotBlank()) {
            token = ""
        } else {
            token = authViewModel.getToken()
            Log.i("token", token)
        }
    }

    Scaffold(floatingActionButton = {
        FloatingActionButton(onClick = { handleClick() }) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onPrimaryContainer
            )
        }
    }, topBar = {
        TopAppBar(
            title = {
                Text(text = "Property overview")
            },
            colors = TopAppBarDefaults.smallTopAppBarColors(
                containerColor = MaterialTheme.colorScheme.surfaceVariant,
                titleContentColor = MaterialTheme.colorScheme.onSurfaceVariant
            )
        )
    }) { values ->

        LazyColumn(contentPadding = values) {
            items(properties.size) { index ->
                PropertyCard(
                    modifier = Modifier.padding(16.dp),
                    property = properties[index],
                    navigateTo = navigateTo
                )
            }
        }
    }
}
