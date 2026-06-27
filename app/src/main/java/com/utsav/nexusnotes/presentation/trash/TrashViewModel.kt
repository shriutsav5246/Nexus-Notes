package com.utsav.nexusnotes.presentation.trash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.utsav.nexusnotes.domain.usecase.note.NoteUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class TrashViewModel @Inject constructor(
    private val noteUseCases: NoteUseCases
) : ViewModel() {

    private val _state = MutableStateFlow(TrashUiState())

    val state: StateFlow<TrashUiState> =
        _state.asStateFlow()

    init {

        noteUseCases.getTrashNotes()

            .onEach { notes ->

                android.util.Log.d(
                    "TRASH_TEST",
                    "Trash Count = ${notes.size}"
                )

                notes.forEach {
                    android.util.Log.d(
                        "TRASH_TEST",
                        "Note -> ${it.id} ${it.title}"
                    )
                }

                _state.update {
                    it.copy(
                        notes = notes,
                        isLoading = false
                    )
                }
            }

            .launchIn(viewModelScope)

    }

    fun showRestoreDialog(noteId: Long) {

        _state.update {

            it.copy(
                showRestoreDialog = true,
                selectedNoteId = noteId
            )

        }

    }

    fun hideRestoreDialog() {

        _state.update {
            it.copy(
                showRestoreDialog = false,
                selectedNoteId = null
            )
        }
    }
    fun restoreSelectedNote() {
        val noteId = _state.value.selectedNoteId ?: return
        viewModelScope.launch {
            noteUseCases.restoreNote(noteId)
            _state.update {
                it.copy(
                    showRestoreDialog = false,
                    selectedNoteId = null
                )
            }
        }
    }
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
    fun restoreSelectedNotes() {
        viewModelScope.launch {
            _state.value.selectedNotes.forEach { noteId ->
                noteUseCases.restoreNote(noteId)
            }
            clearSelection()
        }
    }
    fun permanentlyDeleteSelectedNotes() {
        viewModelScope.launch {
            _state.value.selectedNotes.forEach { noteId ->
                noteUseCases.permanentDelete(noteId)
            }
            clearSelection()
        }
    }
    fun showDeleteAllDialog() {

        _state.update {
            it.copy(
                showDeleteAllDialog = true
            )
        }

    }
    fun hideDeleteAllDialog() {

        _state.update {
            it.copy(
                showDeleteAllDialog = false
            )
        }

    }
    fun permanentlyDeleteAllNotes() {

        viewModelScope.launch {

            noteUseCases.permanentlyDeleteAll()

            _state.update {
                it.copy(
                    showDeleteAllDialog = false
                )
            }

        }

    }
}