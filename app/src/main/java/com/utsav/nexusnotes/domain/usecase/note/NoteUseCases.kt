package com.utsav.nexusnotes.domain.usecase.note

data class NoteUseCases(

    val getNotes: GetNotesUseCase,

    val getTrashNotes: GetTrashNotesUseCase,

    val getNote: GetNoteUseCase,

    val insertNote: InsertNoteUseCase,

    val updateNote: UpdateNoteUseCase,

    val deleteNote: DeleteNoteUseCase,

    val restoreNote: RestoreNoteUseCase,

    val permanentDelete: PermanentDeleteUseCase,

    val permanentlyDeleteAll: PermanentlyDeleteAllUseCase,

    val saveNote: SaveNoteUseCase

)