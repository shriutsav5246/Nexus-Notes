package com.utsav.nexusnotes.di
import com.utsav.nexusnotes.domain.usecase.note.LockNoteUseCase
import com.utsav.nexusnotes.domain.usecase.note.UnlockNoteUseCase
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
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    private val MIGRATION_1_2 = object : Migration(1, 2) {

        override fun migrate(
            database: SupportSQLiteDatabase
        ) {

            database.execSQL(
                """
            ALTER TABLE notes
            ADD COLUMN isLocked INTEGER NOT NULL DEFAULT 0
            """.trimIndent()
            )

        }

    }
    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): NexusDatabase {

        return Room.databaseBuilder(
            context,
            NexusDatabase::class.java,
            "nexus_notes_database"
        )
            .addMigrations(MIGRATION_1_2)
            .build()

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
            lockNote = LockNoteUseCase(repository),
            unlockNote = UnlockNoteUseCase(repository),
            permanentDelete = PermanentDeleteUseCase(repository),
            permanentlyDeleteAll = PermanentlyDeleteAllUseCase(repository),
            saveNote = SaveNoteUseCase(repository)
        )
    }
}