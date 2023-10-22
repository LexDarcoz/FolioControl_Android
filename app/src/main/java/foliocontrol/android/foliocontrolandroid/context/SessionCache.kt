package foliocontrol.android.foliocontrolandroid.context

interface SessionCache {
fun saveSession(session: Session)
fun getActiveSession(): Session?
fun clearSession()

}
