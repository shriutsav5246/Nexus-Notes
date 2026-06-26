package com.utsav.nexusnotes.presentation.home

import com.utsav.nexusnotes.domain.model.Note

data class HomeUiState(

    val notes: List<Note> = emptyList(),

    val isLoading: Boolean = true,

    val isSearching: Boolean = false,

    val searchText: String = "",

    val isSelectionMode: Boolean = false,

    val selectedNotes: Set<Long> = emptySet(),

    val showDeleteDialog: Boolean = false

)