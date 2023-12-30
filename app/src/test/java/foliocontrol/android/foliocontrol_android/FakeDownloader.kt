package foliocontrol.android.foliocontrol_android

import foliocontrol.android.foliocontrolandroid.data.document.Downloader

class FakeDownloader : Downloader {
    override fun downloadFile(url: String): Long {
        if (url == "success") {
            return 1
        }

        return 0
    }
}