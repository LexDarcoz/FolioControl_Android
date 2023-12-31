package foliocontrol.android.foliocontrolandroid.data.document

import android.app.DownloadManager
import android.content.Context
import android.os.Environment
import androidx.core.net.toUri

/**
 * Implementation of the [Downloader] interface for downloading files using Android's DownloadManager.
 *
 * @param context The application context.
 */
class AndroidDownloader(private val context: Context) : Downloader {
    private val downloadManager = context.getSystemService(DownloadManager::class.java)

    /**
     * Initiates the download of a file using Android's DownloadManager.
     *
     * @param url The URL of the file to be downloaded.
     * @return The unique identifier assigned to the download request by the DownloadManager.
     */
    override fun downloadFile(url: String): Long {
        // Create a download request using DownloadManager.Requests
        val request =
            DownloadManager.Request(url.toUri()).setMimeType("application/pdf")
//            .setAllowedNetworkTypes(
//                DownloadManager.Request.NETWORK_WIFI or DownloadManager.Request.NETWORK_MOBILE
//            )
                .setTitle("Download").setDescription("Downloading Property Document...")
//            .addRequestHeader("Authorization", "Bearer ${"token"}")
                .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
                .setDestinationInExternalPublicDir(
                    Environment.DIRECTORY_DOWNLOADS,
                    "foliocontrol_document.pdf",
                ).setAllowedOverMetered(true).setAllowedOverRoaming(true)

        // Enqueue the download request and return the unique download identifier
        return downloadManager.enqueue(request)
    }
}
