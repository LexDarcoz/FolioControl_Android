package foliocontrol.android.foliocontrolandroid.data.document

interface Downloader {
    /**
     * Initiates a file download from the specified URL.
     *
     * @param url The URL of the file to be downloaded.
     * @return The download ID assigned by the DownloadManager.
     */
    fun downloadFile(url: String): Long
}
