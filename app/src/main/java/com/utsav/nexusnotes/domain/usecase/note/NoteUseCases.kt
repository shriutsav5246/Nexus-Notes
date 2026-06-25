package com.utsav.nexusnotes.domain.usecase.note

data class NoteUseCases(

    val getNotes: GetNotesUseCase,

    val getNote: GetNoteUseCase,

    val insertNote: InsertNoteUseCase,

    val updateNote: UpdateNoteUseCase,

    val deleteNote: DeleteNoteUseCase,

    val saveNote: SaveNoteUseCase

)