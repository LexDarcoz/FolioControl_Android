package foliocontrol.android.foliocontrolandroid.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun SettingScreen() {
    Box(
        modifier = Modifier
            .fillMaxHeight()
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Text(
                text = "Settings",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.primary
            )

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Button(
                        onClick = { /* Delete Account TODO */ },
                        modifier = Modifier.padding(8.dp)
                    ) {
                        Text(text = "Delete Account")
                    }
                    Button(
                        onClick = { /* Change Password TODO */ },
                        modifier = Modifier.padding(8.dp)
                    ) {
                        Text(text = "Change Password")
                    }
                }

                Column {
                    Button(
                        onClick = { /* Leave Partnership TODO */ },
                        modifier = Modifier.padding(8.dp)
                    ) {
                        Text(text = "Leave Partnership")
                    }
                    Button(
                        onClick = { /* Delete Partnership TODO */ },
                        modifier = Modifier.padding(8.dp)
                    ) {
                        Text(text = "Delete Partnership")
                    }
                }
            }
        }
    }
}
