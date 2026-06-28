package com.utsav.nexusnotes.presentation.editor

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.utsav.nexusnotes.domain.model.NoteDraft
import com.utsav.nexusnotes.domain.usecase.note.NoteUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditorViewModel @Inject constructor(
    private val noteUseCases: NoteUseCases
) : ViewModel() {

    private var initialized = false

    private val _state = MutableStateFlow(EditorUiState())

    val state: StateFlow<EditorUiState> =
        _state.asStateFlow()

    private var autoSaveJob: Job? = null

    private val _navigateBack = MutableSharedFlow<Unit>()

    val navigateBack =
        _navigateBack.asSharedFlow()

    fun initialize(noteId: Long) {

        if (initialized) return

        initialized = true

        if (noteId == 0L) return

        viewModelScope.launch {

            val note = noteUseCases.getNote(noteId)

            note?.let {

                _state.update { current ->

                    current.copy(
                        noteId = it.id,
                        title = it.title,
                        content = it.content,
                        isNewNote = false,
                        hasChanges = false,
                        isLocked = it.isLocked
                    )

                }

            }

        }

    }

    fun onEvent(event: EditorUiEvent) {

        when (event) {

            is EditorUiEvent.TitleChanged -> {

                _state.update {

                    it.copy(
                        title = event.value,
                        hasChanges = true
                    )

                }

                scheduleAutoSave()

            }

            is EditorUiEvent.ContentChanged -> {

                _state.update {

                    it.copy(
                        content = event.value,
                        hasChanges = true
                    )

                }

                scheduleAutoSave()

            }

            EditorUiEvent.ToggleLock -> {

                _state.update {

                    it.copy(
                        showLockDialog = true
                    )

                }

            }

            EditorUiEvent.DismissLockDialog -> {

                _state.update {

                    it.copy(
                        showLockDialog = false
                    )

                }

            }

            EditorUiEvent.ConfirmLock -> {

                viewModelScope.launch {

                    val current = state.value

                    if (current.noteId == 0L) {

                        _state.update {

                            it.copy(
                                showLockDialog = false
                            )

                        }

                        return@launch

                    }

                    if (current.isLocked) {

                        noteUseCases.unlockNote(current.noteId)

                    } else {
                        android.util.Log.d(
                            "LOCK_TEST",
                            "Lock button pressed for noteId = ${current.noteId}"
                        )
                        noteUseCases.lockNote(current.noteId)

                    }

                    _state.update {

                        it.copy(
                            isLocked = !current.isLocked,
                            showLockDialog = false
                        )

                    }

                }

            }

            EditorUiEvent.BackPressed -> {

                viewModelScope.launch {

                    handleBackPressed()

                    _navigateBack.emit(Unit)

                }

            }

        }

    }

    private fun scheduleAutoSave() {

        autoSaveJob?.cancel()

        autoSaveJob = viewModelScope.launch {

            delay(500)

            saveNote()

        }

    }

    private suspend fun handleBackPressed() {

        autoSaveJob?.cancel()

        val current = state.value

        if (
            current.title.isBlank() &&
            current.content.isBlank()
        ) {

            if (current.noteId != 0L) {

                noteUseCases.deleteNote(current.noteId)

            }

            return

        }

        saveNote()

    }

    private suspend fun saveNote() {

        val current = state.value

        if (
            current.title.isBlank() &&
            current.content.isBlank()
        ) return

        val savedId = noteUseCases.saveNote(

            NoteDraft(
                id = current.noteId,
                title = current.title,
                content = current.content
            )

        )

        if (current.noteId == 0L) {

            _state.update {

                it.copy(
                    noteId = savedId,
                    isNewNote = false,
                    hasChanges = false
                )

            }

        } else {

            _state.update {

                it.copy(
                    hasChanges = false
                )

            }

        }

    }

}