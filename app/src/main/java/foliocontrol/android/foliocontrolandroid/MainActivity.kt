package foliocontrol.android.foliocontrolandroid

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import foliocontrol.android.foliocontrolandroid.ui.theme.FolioControlAndroidTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appContext = applicationContext
        setContent {
            FolioControlAndroidTheme {
                Surface(
                    modifier = Modifier.fillMaxSize()

                ) {
                    FolioControlApplication()
                }
            }
        }
    }

    companion object {

        lateinit var appContext: Context
    }
}
