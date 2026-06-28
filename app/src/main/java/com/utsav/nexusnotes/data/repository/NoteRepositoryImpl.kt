package com.utsav.nexusnotes.data.repository

import com.utsav.nexusnotes.data.local.dao.NoteDao
import com.utsav.nexusnotes.data.mapper.toDomain
import com.utsav.nexusnotes.data.mapper.toEntity
import com.utsav.nexusnotes.domain.model.Note
import com.utsav.nexusnotes.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class NoteRepositoryImpl @Inject constructor(
    private val noteDao: NoteDao
) : NoteRepository {

    override fun getAllNotes(): Flow<List<Note>> =
        noteDao.getAllNotes().map { notes ->
            notes.map { it.toDomain() }
        }



    override suspend fun getNoteById(id: Long): Note? =
        noteDao.getNoteById(id)?.toDomain()

    override suspend fun insertNote(
        note: Note
    ): Long {

        return noteDao.insertNote(
            note.toEntity()
        )

    }

    override suspend fun updateNote(note: Note) {
        noteDao.updateNote(note.toEntity())
    }

    override suspend fun moveToTrash(noteId: Long) {
        android.util.Log.d(
            "DELETE_TEST",
            "Repository moveToTrash() -> $noteId"
        )

        noteDao.moveToTrash(noteId)

        android.util.Log.d(
            "DELETE_TEST",
            "Repository Finished"
        )
    }

    override suspend fun restoreNote(noteId: Long) {
        noteDao.restoreNote(noteId)
    }

    override suspend fun lockNote(noteId: Long) {
        android.util.Log.d(
            "LOCK_TEST",
            "Repository lockNote($noteId)"
        )
        noteDao.lockNote(noteId)
    }

    override suspend fun unlockNote(noteId: Long) {
        noteDao.unlockNote(noteId)
    }

    override suspend fun permanentlyDelete(noteId: Long) {
        noteDao.permanentlyDelete(noteId)
    }

    override fun getDeletedNotes(): Flow<List<Note>> =
        noteDao.getDeletedNotes().map { notes ->
            notes.map { it.toDomain() }
        }
    override suspend fun permanentlyDeleteAll() {
        noteDao.permanentlyDeleteAll()
    }

}