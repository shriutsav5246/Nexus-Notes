package com.utsav.nexusnotes.presentation.editor

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.utsav.nexusnotes.domain.model.NoteDraft
import com.utsav.nexusnotes.domain.usecase.note.NoteUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditorViewModel @Inject constructor(
    private val noteUseCases: NoteUseCases
) : ViewModel() {

    private val _state = MutableStateFlow(EditorUiState())

    val state: StateFlow<EditorUiState> =
        _state.asStateFlow()

    private var autoSaveJob: Job? = null

    fun onEvent(
        event: EditorUiEvent
    ) {

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

        }

    }

    private fun scheduleAutoSave() {

        autoSaveJob?.cancel()

        autoSaveJob = viewModelScope.launch {

            delay(500)

            saveNote()

        }

    }

    private suspend fun saveNote() {

        val current = state.value

        if (
            current.title.isBlank() &&
            current.content.isBlank()
        ) {
            return
        }

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