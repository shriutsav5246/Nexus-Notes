package com.utsav.nexusnotes.presentation.editor

data class EditorUiState(

    val noteId: Long = 0L,

    val title: String = "",

    val content: String = "",

    val isLoading: Boolean = false,

    val isNewNote: Boolean = true,

    val hasChanges: Boolean = false

)