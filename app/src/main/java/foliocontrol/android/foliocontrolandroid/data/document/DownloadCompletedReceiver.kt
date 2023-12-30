package foliocontrol.android.foliocontrolandroid.data.document

import android.app.DownloadManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class DownloadCompleteReceiver : BroadcastReceiver() {

    @Suppress("ktlint:standard:function-signature")
    override fun onReceive(context: Context?, intent: Intent?) {
        // Check if the received action is "android.intent.action.DOWNLOAD_COMPLETE"
        if (intent?.action == DownloadManager.ACTION_DOWNLOAD_COMPLETE) {
            // Retrieve the download ID from the intent
            val downloadId = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1L)

            // Display a notification with download details
            if (downloadId != -1L) {
                println("Download Complete")
//                showNotification(context, downloadId)
            }
        }
//    }
//
//    @SuppressLint("Range")
//    private fun showNotification(
//        @Suppress("ktlint:standard:function-signature") context: Context?,
//        @Suppress("ktlint:standard:annotation") downloadId: @Suppress("ktlint:standard:wrapping") Long
//    ) {
//        // Create a notification channel (required for Android 8.0 and higher)
//        createNotificationChannel(context)
//
//        // Get the DownloadManager service
//        val downloadManager = context?.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
//
//        // Get the download details using the download ID
//        val query = DownloadManager.Query().setFilterById(downloadId)
//        val cursor = downloadManager.query(query)
//        if (cursor.moveToFirst()) {
//            // Extract download information
//            val status = cursor.getInt(cursor.getColumnIndex(DownloadManager.COLUMN_STATUS))
//            val title = cursor.getString(cursor.getColumnIndex(DownloadManager.COLUMN_TITLE))
//            val uriString =
//                cursor.getString(cursor.getColumnIndex(DownloadManager.COLUMN_LOCAL_URI))
//
//            // Create a notification intent to open the downloaded file
//            val uri = Uri.parse(uriString)
//            val intent = Intent(Intent.ACTION_VIEW).setDataAndType(uri, getMimeType(uri, context))
//                .addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
//
//            // Create a PendingIntent for the notification
//            val pendingIntent = if (status == DownloadManager.STATUS_SUCCESSFUL) {
//                PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_IMMUTABLE)
//            } else {
//                // If the download failed, open the app's settings
//                val settingsIntent =
//                    @Suppress("ktlint:standard:multiline-expression-wrapping") Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).setData(
//                        Uri.fromParts("package", context.packageName, null)
//                    )
//                PendingIntent.getActivity(context, 0, settingsIntent, PendingIntent.FLAG_IMMUTABLE)
//            }
//
//            // Create and display the notification
//            val notification =
//                NotificationCompat.Builder(context, CHANNEL_ID).setContentTitle("Download Complete")
//                    .setContentText("Downloaded: $title")
//                    .setSmallIcon(android.R.drawable.stat_sys_download_done)
//                    .setPriority(NotificationCompat.PRIORITY_HIGH).setContentIntent(pendingIntent)
//                    .setAutoCancel(true).build()
//
//            // Show the notification
//            val notificationManager = NotificationManagerCompat.from(context)
//            if (ActivityCompat.checkSelfPermission(
//                    context, Manifest.permission.POST_NOTIFICATIONS
//                ) != PackageManager.PERMISSION_GRANTED
//            ) {
//                // TODO: Consider calling
//                //    ActivityCompat#requestPermissions
//                // here to request the missing permissions, and then overriding
//                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
//                //                                          int[] grantResults)
//                // to handle the case where the user grants the permission. See the documentation
//                // for ActivityCompat#requestPermissions for more details.
//                return
//            }
//            notificationManager.notify(NOTIFICATION_ID, notification)
//        }
//
//        // Close the cursor
//        cursor.close()
//    }
//
//    private fun createNotificationChannel(context: Context?) {
//        // Create a notification channel for Android 8.0 and higher
//        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
//            val channel = NotificationChannel(
//                CHANNEL_ID, "Download Channel", NotificationManager.IMPORTANCE_HIGH
//            )
//            val notificationManager =
//                context?.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
//            notificationManager.createNotificationChannel(channel)
//        }
//    }
//
//    private fun getMimeType(uri: Uri, context: Context?): String? {
//        // Get MIME type from the file extension
//        return if (uri.scheme == ContentResolver.SCHEME_CONTENT) {
//            val cr = context?.contentResolver
//            cr?.getType(uri)
//        } else {
//            val fileExtension = MimeTypeMap.getFileExtensionFromUrl(uri.toString())
//            MimeTypeMap.getSingleton().getMimeTypeFromExtension(fileExtension.toLowerCase())
//        }
//    }
//
//    companion object {
//        private const val CHANNEL_ID = "download_channel"
//        private const val NOTIFICATION_ID = 1
//    }
    }
}
