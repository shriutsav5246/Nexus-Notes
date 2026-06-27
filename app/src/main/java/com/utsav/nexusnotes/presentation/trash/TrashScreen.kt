package com.utsav.nexusnotes.presentation.trash

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.utsav.nexusnotes.presentation.trash.components.EmptyTrash
import com.utsav.nexusnotes.presentation.trash.components.TrashList
import com.utsav.nexusnotes.presentation.trash.components.TrashTopBar
import com.utsav.nexusnotes.presentation.trash.components.RestoreNoteDialog
import com.utsav.nexusnotes.presentation.trash.components.DeleteAllTrashDialog
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TrashScreen(
    onBack: () -> Unit,
    viewModel: TrashViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    Scaffold(
        topBar = {
            TrashTopBar(
                isSelectionMode = state.isSelectionMode,
                selectedCount = state.selectedNotes.size,
                onBackClick = {
                    if (state.isSelectionMode) {
                        viewModel.clearSelection()
                    } else {
                        onBack()
                    }
                },
                onRestoreClick = {
                    viewModel.restoreSelectedNotes()
                },
                onDeleteClick = {
                    viewModel.permanentlyDeleteSelectedNotes()
                },
                onDeleteAllClick = {
                    viewModel.showDeleteAllDialog()
                }
            )
        }
    ) { padding ->
        if (state.notes.isEmpty()) {
            EmptyTrash()
        } else {
            TrashList(
                notes = state.notes,
                padding = padding,
                selectedNotes = state.selectedNotes,
                isSelectionMode = state.isSelectionMode,
                onNoteClick = { noteId ->
                    viewModel.showRestoreDialog(noteId)
                },
                onNoteLongClick = { noteId ->
                    viewModel.onNoteLongClick(noteId)
                },
                onSelectionClick = { noteId ->
                    viewModel.onSelectionClick(noteId)
                }
            )
        }
    }
    RestoreNoteDialog(
        visible = state.showRestoreDialog,
        onDismiss = {
            viewModel.hideRestoreDialog()
        },
        onConfirm = {
            viewModel.restoreSelectedNote()
        }
    )
    DeleteAllTrashDialog(
        visible = state.showDeleteAllDialog,
        onDismiss = {
            viewModel.hideDeleteAllDialog()
        },
        onConfirm = {
            viewModel.permanentlyDeleteAllNotes()
        }
    )
}