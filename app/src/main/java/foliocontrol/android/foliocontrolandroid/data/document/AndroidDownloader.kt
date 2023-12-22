package foliocontrol.android.foliocontrolandroid.data.document

import android.app.DownloadManager
import android.content.Context
import android.os.Environment
import androidx.core.net.toUri

class AndroidDownloader(private val context: Context) : Downloader {
    private val downloadManager = context.getSystemService(DownloadManager::class.java)

    override fun downloadFile(url: String): Long {
        val request = DownloadManager.Request(url.toUri()).setMimeType("application/pdf")
//            .setAllowedNetworkTypes(
//                DownloadManager.Request.NETWORK_WIFI or DownloadManager.Request.NETWORK_MOBILE
//            )
            .setTitle("Download").setDescription("Downloading Property Document...")
//            .addRequestHeader("Authorization", "Bearer ${"token"}")
            .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE)
            .setDestinationInExternalPublicDir(
                Environment.DIRECTORY_DOWNLOADS,
                "foliocontrol_document.pdf"
            ).setAllowedOverMetered(true).setAllowedOverRoaming(true)
        return downloadManager.enqueue(request)
    }
}
