package com.utsav.nexusnotes.domain.usecase.note

import com.utsav.nexusnotes.domain.model.Note
import com.utsav.nexusnotes.domain.repository.NoteRepository
import javax.inject.Inject

class DeleteNoteUseCase @Inject constructor(
    private val repository: NoteRepository
) {

    suspend operator fun invoke(note: Note) {
        repository.deleteNote(note)
    }

}