package com.utsav.nexusnotes.domain.repository

import com.utsav.nexusnotes.domain.model.Note
import kotlinx.coroutines.flow.Flow

interface NoteRepository {

    fun getAllNotes(): Flow<List<Note>>

    suspend fun getNoteById(
        id: Long
    ): Note?

    suspend fun insertNote(
        note: Note
    ): Long

    suspend fun updateNote(
        note: Note
    )

    suspend fun moveToTrash(noteId: Long)

    suspend fun restoreNote(noteId: Long)

    suspend fun permanentlyDelete(noteId: Long)

    fun getDeletedNotes(): Flow<List<Note>>

    suspend fun permanentlyDeleteAll()

}