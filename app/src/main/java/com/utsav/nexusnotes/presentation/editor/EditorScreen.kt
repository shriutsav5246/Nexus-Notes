package com.utsav.nexusnotes.presentation.editor

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.utsav.nexusnotes.presentation.editor.components.EditorTopBar
import com.utsav.nexusnotes.presentation.editor.components.NoteEditor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditorScreen(

    noteId: Long,

    onBack: () -> Unit,

    viewModel: EditorViewModel = hiltViewModel()

) {

    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(noteId) {
        viewModel.initialize(noteId)
    }

    LaunchedEffect(Unit) {

        viewModel.navigateBack.collect {

            onBack()

        }

    }

    BackHandler {

        viewModel.onEvent(
            EditorUiEvent.BackPressed
        )

    }

    Scaffold(

        modifier = Modifier
            .fillMaxSize()
            .windowInsetsPadding(WindowInsets.safeDrawing),

        topBar = {

            EditorTopBar(

                onBackClick = {
                    viewModel.onEvent(
                        EditorUiEvent.BackPressed
                    )
                },

                isLocked = state.isLocked,

                onLockClick = {

                    viewModel.onEvent(
                        EditorUiEvent.ToggleLock
                    )

                }

            )

        }

    )
    { padding ->

        NoteEditor(

            modifier = Modifier.padding(padding),

            title = state.title,

            content = state.content,

            onTitleChange = {

                viewModel.onEvent(
                    EditorUiEvent.TitleChanged(it)
                )

            },

            onContentChange = {

                viewModel.onEvent(
                    EditorUiEvent.ContentChanged(it)
                )

            }

        )

    }
    if (state.showLockDialog) {

        androidx.compose.material3.AlertDialog(

            onDismissRequest = {

                viewModel.onEvent(
                    EditorUiEvent.DismissLockDialog
                )

            },

            title = {

                androidx.compose.material3.Text(
                    if (state.isLocked)
                        "Unlock Note"
                    else
                        "Lock Note"
                )

            },

            text = {

                androidx.compose.material3.Text(
                    if (state.isLocked)
                        "Unlocking is not implemented yet."
                    else
                        "Locking is not implemented yet."
                )

            },

            confirmButton = {

                androidx.compose.material3.Button(

                    onClick = {

                        viewModel.onEvent(
                            EditorUiEvent.ConfirmLock
                        )

                    }

                ) {

                    androidx.compose.material3.Text("OK")

                }

            },

            dismissButton = {

                androidx.compose.material3.Button(

                    onClick = {

                        viewModel.onEvent(
                            EditorUiEvent.DismissLockDialog
                        )

                    }

                ) {

                    androidx.compose.material3.Text("Cancel")

                }

            }

        )

    }

}