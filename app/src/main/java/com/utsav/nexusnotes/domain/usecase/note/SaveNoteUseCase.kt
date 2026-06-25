package com.utsav.nexusnotes.domain.usecase.note

import com.utsav.nexusnotes.domain.model.Note
import com.utsav.nexusnotes.domain.model.NoteColor
import com.utsav.nexusnotes.domain.model.NoteDraft
import com.utsav.nexusnotes.domain.repository.NoteRepository
import javax.inject.Inject

class SaveNoteUseCase @Inject constructor(
    private val repository: NoteRepository
) {

    suspend operator fun invoke(
        draft: NoteDraft
    ): Long {

        val now = System.currentTimeMillis()

        if (draft.id == 0L) {

            val note = Note(
                id = 0,
                title = draft.title,
                content = draft.content,
                color = NoteColor.DEFAULT,
                createdAt = now,
                updatedAt = now,
                isDeleted = false
            )

            return repository.insertNote(note)

        }

        val oldNote = repository.getNoteById(draft.id)

        val updatedNote = Note(

            id = draft.id,

            title = draft.title,

            content = draft.content,

            color = oldNote?.color ?: NoteColor.DEFAULT,

            createdAt = oldNote?.createdAt ?: now,

            updatedAt = now,

            isDeleted = oldNote?.isDeleted ?: false

        )

        repository.updateNote(updatedNote)

        return draft.id

    }

}