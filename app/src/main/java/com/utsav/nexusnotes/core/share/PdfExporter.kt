package com.utsav.nexusnotes.core.share

import android.content.Context
import android.graphics.Paint
import android.graphics.pdf.PdfDocument
import com.utsav.nexusnotes.domain.model.Note
import java.io.File

class PdfExporter(
    private val context: Context
)
{
    private companion object {

        const val PAGE_WIDTH = 595
        const val PAGE_HEIGHT = 842

        const val LEFT_MARGIN = 45f
        const val RIGHT_MARGIN = 550f

        const val TOP_MARGIN = 55f

        const val HEADER_BOTTOM = 85f
        const val CONTENT_BOTTOM = 730f

        const val FOOTER_TOP = 760f

        const val FOOTER_TEXT_1 = 792f
        const val FOOTER_TEXT_2 = 812f

        const val LINE_HEIGHT = 22f

    }
    private data class PdfPageState(

        var pageNumber: Int,

        var page: PdfDocument.Page,

        var canvas: android.graphics.Canvas,

        var y: Float

    )

    fun export(
        notes: List<Note>
    ): File {
        val document = PdfDocument()
        val titlePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
            textSize = 22f
            isFakeBoldText = true
            textAlign = Paint.Align.CENTER
        }
        val headingPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
            textSize = 17f
            isFakeBoldText = true
        }
        val bodyPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
            textSize = 14f
        }
        val footerPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
            textSize = 11f
            textAlign = Paint.Align.CENTER
        }
        notes.forEachIndexed { index, note ->

            val pageInfo = PdfDocument.PageInfo.Builder(
                PAGE_WIDTH,
                PAGE_HEIGHT,
                index + 1
            ).create()
            val page = document.startPage(pageInfo)
            val canvas = page.canvas
            drawHeader(
                canvas,
                titlePaint,
                bodyPaint
            )
            var y = HEADER_BOTTOM + 40f
            // Title
            canvas.drawText(
                "Title",
                LEFT_MARGIN,
                y,
                headingPaint
            )
            y += 25f
            val title =
                if (note.title.isBlank()) {
                    "Untitled Note"
                } else {
                    note.title
                }
            val wrappedTitle = wrapText(
                title,
                bodyPaint,
                RIGHT_MARGIN - LEFT_MARGIN
            )
            var state = PdfPageState(
                pageNumber = index + 1,
                page = page,
                canvas = canvas,
                y = y
            )

            state = drawWrappedText(
                document = document,
                state = state,
                text = title,
                paint = bodyPaint,
                titlePaint = titlePaint,
                bodyPaint = bodyPaint,
                footerPaint = footerPaint
            )

            y = state.y + 20f
            // Content
            canvas.drawText(
                "Content",
                LEFT_MARGIN,
                y,
                headingPaint
            )
            y += 25f
            val wrappedLines = wrapText(
                note.content,
                bodyPaint,
                RIGHT_MARGIN - LEFT_MARGIN
            )
            wrappedLines.forEach { line ->
                if (y >= 740f) {
                    return@forEach
                }
                canvas.drawText(
                    line,
                    LEFT_MARGIN,
                    y,
                    bodyPaint
                )
                y += LINE_HEIGHT
            }
            drawFooter(
                canvas,
                bodyPaint,
                footerPaint
            )
            document.finishPage(page)
        }
        val file = File(
            context.cacheDir,
            "NexusNotes.pdf"
        )
        file.outputStream().use {
            document.writeTo(it)
        }
        document.close()
        return file
    }
    private fun wrapText(
        text: String,
        paint: Paint,
        maxWidth: Float
    ): List<String> {

        val result = mutableListOf<String>()

        text.lines().forEach { paragraph ->

            if (paragraph.isBlank()) {
                result.add("")
                return@forEach
            }

            var currentLine = ""

            paragraph.split(" ").forEach { word ->

                val testLine =
                    if (currentLine.isEmpty()) {
                        word
                    } else {
                        "$currentLine $word"
                    }
                if (paint.measureText(testLine) <= maxWidth) {
                    currentLine = testLine
                } else {
                    result.add(currentLine)
                    currentLine = word
                }
            }
            if (currentLine.isNotEmpty()) {
                result.add(currentLine)
            }
        }
        return result
    }
    private fun drawHeader(
        canvas: android.graphics.Canvas,
        titlePaint: Paint,
        bodyPaint: Paint
    ) {

        canvas.drawText(
            "NEXUS NOTES",
            PAGE_WIDTH / 2f,
            TOP_MARGIN,
            titlePaint
        )

        canvas.drawLine(
            LEFT_MARGIN,
            HEADER_BOTTOM,
            RIGHT_MARGIN,
            HEADER_BOTTOM,
            bodyPaint
        )

    }
    private fun drawFooter(
        canvas: android.graphics.Canvas,
        bodyPaint: Paint,
        footerPaint: Paint
    )
    {

        canvas.drawLine(
            LEFT_MARGIN,
            FOOTER_TOP,
            RIGHT_MARGIN,
            FOOTER_TOP,
            bodyPaint
        )

        canvas.drawText(
            "Nexus Notes By Utsav Shrivastav",
            PAGE_WIDTH / 2f,
            FOOTER_TEXT_1,
            footerPaint
        )
        canvas.drawText(
            "linkedin.com/in/shriutsav5246",
            PAGE_WIDTH / 2f,
            FOOTER_TEXT_2,
            footerPaint
        )
    }
    private fun drawLine(

        state: PdfPageState,

        text: String,

        paint: Paint

    ) {

        state.canvas.drawText(

            text,

            LEFT_MARGIN,

            state.y,

            paint

        )

        state.y += LINE_HEIGHT

    }
    private fun drawWrappedText(

        document: PdfDocument,

        state: PdfPageState,

        text: String,

        paint: Paint,

        titlePaint: Paint,

        bodyPaint: Paint,

        footerPaint: Paint

    ): PdfPageState {

        var currentState = state

        val wrapped = wrapText(

            text,

            paint,

            RIGHT_MARGIN - LEFT_MARGIN

        )

        wrapped.forEach { line ->

            if (currentState.y + LINE_HEIGHT >= CONTENT_BOTTOM) {

                currentState = createNextPage(

                    document,

                    currentState,

                    titlePaint,

                    bodyPaint,

                    footerPaint

                )

            }

            drawLine(

                currentState,

                line,

                paint

            )

        }

        return currentState

    }
    private fun startPage(
        document: PdfDocument,
        pageNumber: Int,
        titlePaint: Paint,
        bodyPaint: Paint
    ): PdfPageState {
        val pageInfo = PdfDocument.PageInfo.Builder(
            PAGE_WIDTH,
            PAGE_HEIGHT,
            pageNumber
        ).create()
        val page = document.startPage(pageInfo)
        val canvas = page.canvas
        drawHeader(
            canvas,
            titlePaint,
            bodyPaint
        )
        return PdfPageState(
            pageNumber = pageNumber,
            page = page,
            canvas = canvas,
            y = HEADER_BOTTOM + 40f
        )
    }
    private fun finishPage(
        document: PdfDocument,
        state: PdfPageState,
        bodyPaint: Paint,
        footerPaint: Paint
    ) {
        drawFooter(
            state.canvas,
            bodyPaint,
            footerPaint
        )
        document.finishPage(
            state.page
        )
    }
    private fun createNextPage(

        document: PdfDocument,

        current: PdfPageState,

        titlePaint: Paint,

        bodyPaint: Paint,

        footerPaint: Paint

    ): PdfPageState {

        finishPage(

            document,

            current,

            bodyPaint,

            footerPaint

        )

        return startPage(

            document,

            current.pageNumber + 1,

            titlePaint,

            bodyPaint

        )

    }
}