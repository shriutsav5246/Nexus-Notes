package com.utsav.nexusnotes.domain.usecase.note

import com.utsav.nexusnotes.domain.model.Note
import com.utsav.nexusnotes.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetNotesUseCase @Inject constructor(
    private val repository: NoteRepository
) {

    operator fun invoke(): Flow<List<Note>> {
        return repository.getAllNotes()
    }

}