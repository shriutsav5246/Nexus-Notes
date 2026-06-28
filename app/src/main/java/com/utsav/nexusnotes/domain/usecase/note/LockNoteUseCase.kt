package com.utsav.nexusnotes.domain.usecase.note

import com.utsav.nexusnotes.domain.repository.NoteRepository
import javax.inject.Inject

class LockNoteUseCase @Inject constructor(

    private val repository: NoteRepository

) {

    suspend operator fun invoke(
        noteId: Long
    ) {

        repository.lockNote(noteId)

    }

}