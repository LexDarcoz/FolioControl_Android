package foliocontrol.android.foliocontrolandroid.screens

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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import foliocontrol.android.foliocontrolandroid.components.PropertyCard
import foliocontrol.android.foliocontrolandroid.context.AuthViewModel
import foliocontrol.android.foliocontrolandroid.context.Token

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(authViewModel: AuthViewModel) {


var token by rememberSaveable {
        mutableStateOf("")
}


    fun handleClick() {
        if (token.isNotBlank()){
            token = ""


        }else{
            token = authViewModel.getToken().token
            Log.i("token", token)
        }

    }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = {handleClick()}) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onPrimaryContainer
                )

            }
        },
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "My Properties")
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant,
                    titleContentColor = MaterialTheme.colorScheme.onSurfaceVariant
                )
            )
        }
    ) { values ->

        LazyColumn(contentPadding = values) {
            items(20) {
                PropertyCard(
                    title = "Gent Aaauugggh",
                    description = "Bacon ipsum dolor amet" +
                        " pork shankle beef " +
                        "andouille ball " +
                        "tip. Meatball corned" +
                        " beef swine, strip steak bacon " +
                        "jerky doner tongue " +
                        "biltong pork loin drumstick sausage " +
                        "hamburger burgdoggen.",
                    modifier = Modifier.padding(16.dp)
                )

                if (token.isNotBlank()){
                    Text(text = token, color = MaterialTheme.colorScheme.primary
                        )
                }
            }
        }
    }
}
