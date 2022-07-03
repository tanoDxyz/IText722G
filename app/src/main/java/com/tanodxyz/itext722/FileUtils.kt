package com.tanodxyz.itext722

import android.content.Context
import android.os.Build
import android.os.Environment
import androidx.annotation.ChecksSdkIntAtLeast
import java.io.File

object FileUtils {

    fun resolveFilesRoot(appContext: Context): File? {
        val externalStorageMounted =
            Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED
        return if (isAndroid10Plus()) {
            if (externalStorageMounted) {
                appContext.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS)
            } else {
                appContext.filesDir
            }
        } else {
            if (externalStorageMounted) {
                Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
            } else {
                appContext.filesDir
            }
        }
    }

    @ChecksSdkIntAtLeast(parameter = 0)
    fun isApiVersionEqualOrHigher(version: Int) = Build.VERSION.SDK_INT >= version
    fun isAndroid10Plus() = isApiVersionEqualOrHigher(29)
}