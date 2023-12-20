package foliocontrol.android.foliocontrolandroid.data.document

interface Downloader {
    fun downloadFile(url: String): Long
}
