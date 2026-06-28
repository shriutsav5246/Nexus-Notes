package com.utsav.nexusnotes.core.utils

import android.content.Context
import android.content.Intent
import androidx.core.content.FileProvider
import java.io.File

class AppShare {

    companion object {

        fun shareApp(
            context: Context
        ) {

            try {

                val apkFile = File(
                    context.applicationInfo.sourceDir
                )

                val cacheDir = File(
                    context.cacheDir,
                    "shared"
                )

                if (!cacheDir.exists()) {
                    cacheDir.mkdirs()
                }

                val sharedApk = File(
                    cacheDir,
                    "NexusNotes.apk"
                )

                apkFile.copyTo(
                    target = sharedApk,
                    overwrite = true
                )

                val uri = FileProvider.getUriForFile(

                    context,

                    "${context.packageName}.provider",

                    sharedApk

                )

                val shareIntent = Intent(
                    Intent.ACTION_SEND
                ).apply {

                    type =
                        "application/vnd.android.package-archive"

                    putExtra(
                        Intent.EXTRA_STREAM,
                        uri
                    )

                    putExtra(
                        Intent.EXTRA_TEXT,
                        "Try Nexus Notes!"
                    )

                    addFlags(
                        Intent.FLAG_GRANT_READ_URI_PERMISSION
                    )

                }

                context.startActivity(

                    Intent.createChooser(

                        shareIntent,

                        "Share Nexus Notes"

                    )

                )

            } catch (e: Exception) {

                e.printStackTrace()

            }

        }

    }

}