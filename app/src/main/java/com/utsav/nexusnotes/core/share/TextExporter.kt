package com.utsav.nexusnotes.core.share

import android.content.Context
import com.utsav.nexusnotes.domain.model.Note
import java.io.File

class TextExporter(
    private val context: Context
) {

    fun export(
        notes: List<Note>
    ): File {

        val file = File(
            context.cacheDir,
            "NexusNotes.txt"
        )

        val builder = StringBuilder()

        notes.forEachIndexed { index, note ->

            builder.appendLine(
                "Title: ${note.title}"
            )

            builder.appendLine()

            builder.appendLine(
                note.content
            )

            if (index != notes.lastIndex) {

                builder.appendLine()

                builder.appendLine(
                    "----------------------------------------"
                )

                builder.appendLine()

            }

        }

        file.writeText(
            builder.toString()
        )

        return file

    }

}