package com.utsav.nexusnotes.presentation.home

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

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val noteUseCases: NoteUseCases
) : ViewModel() {

    private val _state = MutableStateFlow(HomeUiState())

    val state: StateFlow<HomeUiState> =
        _state.asStateFlow()

    init {
        loadNotes()
    }

    private fun loadNotes() {

        noteUseCases.getNotes()

            .onEach { notes ->

                _state.update {
                    it.copy(
                        notes = notes,
                        isLoading = false
                    )
                }

            }

            .launchIn(viewModelScope)

    }

}