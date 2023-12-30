package foliocontrol.android.foliocontrolandroid

import android.app.Application
import foliocontrol.android.foliocontrolandroid.data.document.AndroidDownloader
import foliocontrol.android.foliocontrolandroid.data.local.RoomContainer
import foliocontrol.android.foliocontrolandroid.data.local.RoomContainerImpl
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob

/**
 * Application class for the FolioControlAndroid app. This class initializes essential components
 * such as the [RoomContainer] and [AndroidDownloader] during the app's lifecycle.
 */
class FolioControlApp : Application() {
    /** Reference to the local Room database container for data storage. */
    lateinit var container: RoomContainer

    /** Instance of the AndroidDownloader for handling file downloads. */
    lateinit var downloader: AndroidDownloader

    /** Coroutine scope for managing asynchronous tasks in the application. */
    private lateinit var appScope: CoroutineScope

    /**
     * Called when the application is starting. Initializes the [appScope], [container], and
     * [downloader] components.
     */
    override fun onCreate() {

        super.onCreate()

        // Create a CoroutineScope with a SupervisorJob to manage coroutine lifecycles.
        appScope = CoroutineScope(SupervisorJob() + Dispatchers.Main)

        // Initialize the RoomContainer for local data storage.
        container = RoomContainerImpl(this, appScope)

        // Initialize the AndroidDownloader for handling file downloads.
        downloader = AndroidDownloader(this)
    }
}
