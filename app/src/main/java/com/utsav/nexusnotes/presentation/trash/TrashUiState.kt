package com.utsav.nexusnotes.presentation.trash

import com.utsav.nexusnotes.domain.model.Note

data class TrashUiState(

    val notes: List<Note> = emptyList(),

    val isLoading: Boolean = true,

    val showRestoreDialog: Boolean = false,

    val selectedNoteId: Long? = null,

    val isSelectionMode: Boolean = false,

    val selectedNotes: Set<Long> = emptySet()

)