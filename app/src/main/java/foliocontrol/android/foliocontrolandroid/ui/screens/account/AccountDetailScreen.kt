package foliocontrol.android.foliocontrolandroid.ui.screens.account

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import foliocontrol.android.foliocontrolandroid.ui.components.FolioTextField
import foliocontrol.android.foliocontrolandroid.ui.viewModels.AccountViewModel

@Composable
fun AccountDetailScreen(accountViewModel: AccountViewModel, navigateTo: (Any?) -> Unit = {}) {
    Box(
        modifier = Modifier.fillMaxSize()

    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            Surface(
                color = MaterialTheme.colorScheme.secondary,
                contentColor = MaterialTheme.colorScheme.primary,
                shape = MaterialTheme.shapes.medium,
                shadowElevation = 4.dp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)

            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                ) {
                    FolioTextField("Name", accountViewModel.user.name) {
                        accountViewModel.handleUserEdit(
                            name  = it
                        )
                    }
                    FolioTextField("LastName", accountViewModel.user.lastName) {
                        accountViewModel.handleUserEdit(
                            lastName = it
                        )
                    }
                    FolioTextField("Email", accountViewModel.user.email) {
                        accountViewModel.handleUserEdit(email = it)
                    }
                    FolioTextField("Street", accountViewModel.user.street) {
                        accountViewModel.handleUserEdit(street = it)
                    }
                    FolioTextField("Street Number", accountViewModel.user.streetNumber) {
                        accountViewModel.handleUserEdit(streetNumber = it)
                    }
                    FolioTextField("Zip Code", accountViewModel.user.zipCode) {
                        accountViewModel.handleUserEdit(
                            zipCode = it
                        )
                    }
                    FolioTextField("City", accountViewModel.user.city) {
                        accountViewModel.handleUserEdit(city = it)
                    }
                    FolioTextField("Country", accountViewModel.user.country) {
                        accountViewModel.handleUserEdit(
                            country = it
                        )
                    }
                    Spacer(modifier = Modifier.weight(1f))
                    Button(
                        onClick = {
                            accountViewModel.handleUserSave()
                            navigateTo("Home")
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 16.dp)
                    ) {
                        Text(text = "Save")
                    }
                }
            }
        }
}}