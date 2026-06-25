package com.utsav.nexusnotes.data.mapper

import com.utsav.nexusnotes.data.local.entity.NoteEntity
import com.utsav.nexusnotes.domain.model.Note
import com.utsav.nexusnotes.domain.model.NoteColor

fun NoteEntity.toDomain(): Note {

    return Note(
        id = id,
        title = title,
        content = content,
        color = NoteColor.entries.firstOrNull {
            it.value == color
        } ?: NoteColor.DEFAULT,
        createdAt = createdAt,
        updatedAt = updatedAt,
        isDeleted = isDeleted
    )

}

fun Note.toEntity(): NoteEntity {

    return NoteEntity(
        id = id,
        title = title,
        content = content,
        color = color.value,
        createdAt = createdAt,
        updatedAt = updatedAt,
        isDeleted = isDeleted
    )

}