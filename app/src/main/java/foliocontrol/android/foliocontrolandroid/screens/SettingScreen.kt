package foliocontrol.android.foliocontrolandroid.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import foliocontrol.android.foliocontrolandroid.components.BottomNavigation

@Composable
fun SettingScreen() {

    Box(modifier = Modifier.padding())
    {
        Text(
            text = "Settings",
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.primary,
            style = MaterialTheme.typography.titleMedium
        )
    }

}
