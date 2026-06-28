package com.utsav.nexusnotes.domain.model

data class Note(

    val id: Long = 0,

    val title: String,

    val content: String,

    val color: NoteColor,

    val createdAt: Long,

    val updatedAt: Long,

    val isDeleted: Boolean = false,

    val isLocked: Boolean = false

)