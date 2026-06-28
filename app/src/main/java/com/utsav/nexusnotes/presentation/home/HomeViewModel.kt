package com.utsav.nexusnotes.presentation.home

import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.filled.Check
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.utsav.nexusnotes.domain.model.Note
import com.utsav.nexusnotes.domain.usecase.note.NoteUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val noteUseCases: NoteUseCases
) : ViewModel() {

    private val _state = MutableStateFlow(HomeUiState())
    private val _events = Channel<HomeScreenEvent>()

    val events = _events.receiveAsFlow()
    val state: StateFlow<HomeUiState> = _state.asStateFlow()

    private var allNotes: List<Note> = emptyList()

    private var recentlyDeletedNotes: List<Note> = emptyList()

    init {
        loadNotes()
    }

    private fun loadNotes() {

        noteUseCases.getNotes()
            .onEach { notes ->

                allNotes = notes

                filterNotes(_state.value.searchText)

            }
            .launchIn(viewModelScope)

    }

    fun onSearchClick() {

        _state.update {

            it.copy(
                isSearching = true
            )

        }

    }

    fun onSearchClose() {

        _state.update {

            it.copy(
                isSearching = false,
                searchText = ""
            )

        }

        filterNotes("")

    }

    fun onSearchTextChange(text: String) {

        android.util.Log.d("SEARCH_TEST", "Typed = $text")

        _state.update {
            it.copy(searchText = text)
        }

        filterNotes(text)
    }

    private fun filterNotes(query: String) {

        val filteredNotes =

            if (query.isBlank()) {

                allNotes

            } else {

                allNotes.filter { note ->

                    note.title.contains(query, ignoreCase = true) ||
                            note.content.contains(query, ignoreCase = true)

                }

            }

        _state.update {

            it.copy(
                notes = filteredNotes,
                isLoading = false
            )

        }

        android.util.Log.d(
            "SEARCH_TEST",
            "Query=$query  All=${allNotes.size}"
        )

        android.util.Log.d(
            "SEARCH_TEST",
            "Filtered=${filteredNotes.size}"
        )

    }
    // --------------------
    // Selection
    // --------------------

    fun onNoteLongClick(noteId: Long) {

        if (!_state.value.isSelectionMode) {

            _state.update {

                it.copy(
                    isSelectionMode = true,
                    selectedNotes = setOf(noteId)
                )

            }

        } else {

            onSelectionClick(noteId)

        }

    }

    fun onSelectionClick(noteId: Long) {

        val selected = _state.value.selectedNotes.toMutableSet()

        if (selected.contains(noteId)) {
            selected.remove(noteId)
        } else {
            selected.add(noteId)
        }

        _state.update {

            it.copy(
                selectedNotes = selected,
                isSelectionMode = selected.isNotEmpty()
            )

        }

    }

    fun clearSelection() {

        _state.update {

            it.copy(
                isSelectionMode = false,
                selectedNotes = emptySet()
            )

        }

    }

    // --------------------
// Delete Dialog
// --------------------

    fun showDeleteDialog() {

        if (_state.value.selectedNotes.isEmpty()) return

        _state.update {

            it.copy(
                showDeleteDialog = true
            )

        }

    }

    fun hideDeleteDialog() {

        _state.update {

            it.copy(
                showDeleteDialog = false
            )

        }

    }

    fun deleteSelectedNotes() {

        val notesToDelete = allNotes.filter {

            it.id in _state.value.selectedNotes

        }

        recentlyDeletedNotes = notesToDelete

        viewModelScope.launch {

            notesToDelete.forEach { note ->
                android.util.Log.d(
                    "DELETE_TEST",
                    "Deleting note = ${note.id}"
                )

                noteUseCases.deleteNote(note.id)

            }

            _state.update {

                it.copy(

                    showDeleteDialog = false,

                    isSelectionMode = false,

                    selectedNotes = emptySet()

                )

            }
            _events.send(
                    HomeScreenEvent.ShowUndoSnackbar
            )
        }

    }

    fun moveSingleNoteToTrash(noteId: Long) {

        val note = allNotes.firstOrNull {

            it.id == noteId

        } ?: return

        recentlyDeletedNotes = listOf(note)

        viewModelScope.launch {

            noteUseCases.deleteNote(note.id)
            _events.send(

                HomeScreenEvent.ShowUndoSnackbar

            )

        }

    }

    fun undoDelete() {

        viewModelScope.launch {

            recentlyDeletedNotes.forEach { note ->

                noteUseCases.updateNote(

                    note.copy(

                        isDeleted = false

                    )

                )

            }

            recentlyDeletedNotes = emptyList()

        }

    }

    fun clearRecentlyDeleted() {

        recentlyDeletedNotes = emptyList()

    }

    fun lockNote(noteId: Long) {

        viewModelScope.launch {

            noteUseCases.lockNote(noteId)

        }

    }

    fun unlockNote(noteId: Long) {

        viewModelScope.launch {

            noteUseCases.unlockNote(noteId)

        }

    }

    fun toggleSelectAll() {

        val allSelected =
            _state.value.selectedNotes.size == allNotes.size

        _state.update {

            it.copy(

                selectedNotes =

                    if (allSelected) {

                        emptySet()

                    } else {

                        allNotes.map { note -> note.id }.toSet()

                    },

                isSelectionMode = !allSelected

            )

        }

    }

    fun isSelected(noteId: Long): Boolean {

        return noteId in _state.value.selectedNotes

    }
}