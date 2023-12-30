package foliocontrol.android.foliocontrolandroid

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import foliocontrol.android.foliocontrolandroid.ui.theme.FolioControlAndroidTheme

/**
 * The main entry point of the Folio Control Android application.
 * Extends [ComponentActivity], which is a base class for activities that integrate with the AndroidX
 * Jetpack Compose UI framework.
 */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        /**
         * Called when the activity is starting. This is where most initialization should go.
         * In this case, it sets up the application context, initializes the theme, and defines the
         * Compose UI hierarchy for the activity.
         *
         * @param savedInstanceState If the activity is being re-initialized after previously being
         * shut down, this Bundle contains the data it most recently supplied in
         * [onSaveInstanceState].
         */
        super.onCreate(savedInstanceState)
        appContext = applicationContext
        setContent {
            FolioControlAndroidTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    FolioControlApplication()
                }
            }
        }
    }

    companion object {
        /**
         * Late-initialized property to hold the application context.
         * Provides global access to the application context throughout the app.
         */
        lateinit var appContext: Context
    }
}
