package foliocontrol.android.foliocontrolandroid

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import foliocontrol.android.foliocontrolandroid.ui.theme.FolioControlAndroidTheme
import retrofit2.*

class MainActivity : ComponentActivity() {
//    private val viewModel by viewModels<AuthViewModel>()

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FolioControlAndroidTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize()

                ) {
                    FolioControlApplication()
                }
            }
        }
    }
}
