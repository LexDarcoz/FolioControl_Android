package foliocontrol.android.foliocontrolandroid

import android.app.Application
import foliocontrol.android.foliocontrolandroid.data.local.RoomContainer
import foliocontrol.android.foliocontrolandroid.data.local.RoomContainerImpl
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob

class FolioControlApp : Application() {
    lateinit var container: RoomContainer
    private lateinit var appScope: CoroutineScope

    override fun onCreate() {
        super.onCreate()
        appScope = CoroutineScope(SupervisorJob() + Dispatchers.Main)
        container = RoomContainerImpl(this, appScope)
    }
}
