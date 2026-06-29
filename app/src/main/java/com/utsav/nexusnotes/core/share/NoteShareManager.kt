package com.utsav.nexusnotes.core.share

import android.content.Context
import android.content.Intent
import androidx.core.content.FileProvider
import java.io.File

class NoteShareManager(
    private val context: Context
) {

    fun shareTextFile(
        file: File
    ) {

        val uri = FileProvider.getUriForFile(
            context,
            "${context.packageName}.provider",
            file
        )

        val intent = Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            putExtra(Intent.EXTRA_STREAM, uri)
            putExtra(
                Intent.EXTRA_SUBJECT,
                "Shared from Nexus Notes"
            )
            clipData = android.content.ClipData.newRawUri(
                "",
                uri
            )
            addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        }
        context.startActivity(
            Intent.createChooser(
                intent,
                "Share Notes"
            )
        )
    }
    fun sharePdfFile(
        file: File
    ) {

        val uri = FileProvider.getUriForFile(
            context,
            "${context.packageName}.provider",
            file
        )

        val intent = Intent(Intent.ACTION_SEND).apply {

            type = "application/pdf"

            putExtra(
                Intent.EXTRA_STREAM,
                uri
            )

            addFlags(
                Intent.FLAG_GRANT_READ_URI_PERMISSION
            )

        }

        val chooser = Intent.createChooser(
            intent,
            "Share Notes"
        ).apply {

            addFlags(
                Intent.FLAG_ACTIVITY_NEW_TASK
            )

        }

        context.startActivity(chooser)

    }

}