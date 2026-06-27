package com.utsav.nexusnotes.di

import android.content.Context
import androidx.room.Room
import com.utsav.nexusnotes.data.local.dao.NoteDao
import com.utsav.nexusnotes.data.local.database.NexusDatabase
import com.utsav.nexusnotes.data.repository.NoteRepositoryImpl
import com.utsav.nexusnotes.domain.repository.NoteRepository
import com.utsav.nexusnotes.domain.usecase.note.DeleteNoteUseCase
import com.utsav.nexusnotes.domain.usecase.note.SaveNoteUseCase
import com.utsav.nexusnotes.domain.usecase.note.GetNoteUseCase
import com.utsav.nexusnotes.domain.usecase.note.GetNotesUseCase
import com.utsav.nexusnotes.domain.usecase.note.InsertNoteUseCase
import com.utsav.nexusnotes.domain.usecase.note.NoteUseCases
import com.utsav.nexusnotes.domain.usecase.note.UpdateNoteUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import com.utsav.nexusnotes.domain.usecase.note.GetTrashNotesUseCase
import com.utsav.nexusnotes.domain.usecase.note.RestoreNoteUseCase
import com.utsav.nexusnotes.domain.usecase.note.PermanentDeleteUseCase
import com.utsav.nexusnotes.domain.usecase.note.PermanentlyDeleteAllUseCase
@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): NexusDatabase {

        return Room.databaseBuilder(
            context,
            NexusDatabase::class.java,
            "nexus_notes_database"
        ).build()

    }
    @Provides
    @Singleton
    fun provideNoteDao(
        database: NexusDatabase
    ): NoteDao {

        return database.noteDao()

    }
    @Provides
    @Singleton
    fun provideRepository(
        noteDao: NoteDao
    ): NoteRepository {
        return NoteRepositoryImpl(noteDao)
    }
    @Provides
    @Singleton
    fun provideNoteUseCases(
        repository: NoteRepository
    ): NoteUseCases {
        return NoteUseCases(
            getNotes = GetNotesUseCase(repository),
            getTrashNotes = GetTrashNotesUseCase(repository),
            getNote = GetNoteUseCase(repository),
            insertNote = InsertNoteUseCase(repository),
            updateNote = UpdateNoteUseCase(repository),
            deleteNote = DeleteNoteUseCase(repository),
            restoreNote = RestoreNoteUseCase(repository),
            permanentDelete = PermanentDeleteUseCase(repository),
            permanentlyDeleteAll = PermanentlyDeleteAllUseCase(repository),
            saveNote = SaveNoteUseCase(repository)
        )
    }
}