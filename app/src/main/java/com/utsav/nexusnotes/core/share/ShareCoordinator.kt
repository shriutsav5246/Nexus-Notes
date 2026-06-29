package com.utsav.nexusnotes.core.share
import android.content.Context
import com.utsav.nexusnotes.domain.model.Note
class ShareCoordinator(
    context: Context
) {
    private val textExporter = TextExporter(context)
    private val pdfExporter = PdfExporter(context)
    private val noteShareManager = NoteShareManager(context)
    fun shareAsText(
        notes: List<Note>
    ) {
        if (notes.isEmpty()) return
        val file = textExporter.export(notes)
        noteShareManager.shareTextFile(file)
    }
    fun shareAsPdf(
        notes: List<Note>
    ) {
        if (notes.isEmpty()) return
        val file = pdfExporter.export(notes)
        noteShareManager.sharePdfFile(file)
    }
}