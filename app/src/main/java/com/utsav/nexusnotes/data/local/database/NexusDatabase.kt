package com.utsav.nexusnotes.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.utsav.nexusnotes.data.local.dao.NoteDao
import com.utsav.nexusnotes.data.local.entity.NoteEntity

@Database(
    entities = [NoteEntity::class],
    version = 2,
    exportSchema = false
)
abstract class NexusDatabase : RoomDatabase() {

    abstract fun noteDao(): NoteDao

}