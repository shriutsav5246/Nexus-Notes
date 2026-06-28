package com.utsav.nexusnotes.presentation.home
import com.utsav.nexusnotes.presentation.home.components.DeleteNotesDialog
import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.Crossfade
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.utsav.nexusnotes.presentation.home.components.EmptyHome
import com.utsav.nexusnotes.presentation.home.components.HomeTopBar
import com.utsav.nexusnotes.presentation.home.components.NotesList
import com.utsav.nexusnotes.presentation.home.components.SearchTopBar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.remember
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalNavigationDrawer
import com.utsav.nexusnotes.presentation.home.components.HomeDrawer
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.rememberCoroutineScope
import kotlinx.coroutines.launch
import androidx.compose.material3.SnackbarDuration
import androidx.compose.ui.platform.LocalContext
import com.utsav.nexusnotes.core.utils.AppShare
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    onAddClick: () -> Unit,
    onNoteClick: (Long) -> Unit,
    onTrashClick: () -> Unit,
    onSettingsClick: () -> Unit,
    onAboutClick: () -> Unit,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val listState = rememberLazyListState()
    val snackbarHostState = remember { SnackbarHostState() }
    val drawerState = rememberDrawerState(
        initialValue = DrawerValue.Closed
    )
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    LaunchedEffect(Unit) {
        viewModel.events.collect { event ->
            when (event) {
                HomeScreenEvent.ShowUndoSnackbar -> {
                    val result = snackbarHostState.showSnackbar(
                        message = "Notes moved to Trash",
                        actionLabel = "UNDO",
                        duration = SnackbarDuration.Long
                    )
                    if (result == SnackbarResult.ActionPerformed) {
                        viewModel.undoDelete()
                    } else {
                        viewModel.clearRecentlyDeleted()
                    }
                }
            }
        }
    }
    BackHandler(
        enabled = state.isSelectionMode
    ) {
        viewModel.clearSelection()
    }
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            HomeDrawer(
                isHomeSelected = true,
                isTrashSelected = false,

                onHomeClick = {
                    scope.launch {
                        drawerState.close()
                    }
                },
                onTrashClick = {
                    scope.launch {
                        drawerState.close()
                    }
                    onTrashClick()
                },
                onAboutClick = {

                    scope.launch {
                        drawerState.close()
                    }

                    onAboutClick()

                },
                onShareClick = {

                    scope.launch {
                        drawerState.close()
                    }

                    AppShare.shareApp(context)

                }
            )
        }
    ) {
        Scaffold(
            snackbarHost = {
                SnackbarHost(
                    hostState = snackbarHostState
                )
            },
            topBar = {
                Crossfade(
                    targetState = state.isSearching,
                    label = "TopBar"
                ) { searching ->
                    if (searching) {
                        SearchTopBar(
                            query = state.searchText,
                            onQueryChange = viewModel::onSearchTextChange,
                            onCloseClick = viewModel::onSearchClose
                        )
                    } else {
                        HomeTopBar(
                            isSelectionMode = state.isSelectionMode,
                            selectedCount = state.selectedNotes.size,
                            allSelected =
                                state.notes.isNotEmpty() &&
                                        state.selectedNotes.size == state.notes.size,
                            onBackClick = {
                                viewModel.clearSelection()
                            },
                            onMenuClick = {
                                scope.launch {
                                    drawerState.open()
                                }
                            },
                            onSearchClick = {
                                if (!state.isSelectionMode) {
                                    viewModel.onSearchClick()
                                }
                            },
                            onSettingsClick = {
                                onSettingsClick()
                            },
                            onDeleteClick = {
                                viewModel.showDeleteDialog()
                            },
                            onSelectAllClick = {
                                viewModel.toggleSelectAll()
                            }
                        )
                    }
                }
            },
            floatingActionButton = {
                AnimatedVisibility(
                    visible = !listState.isScrollInProgress,
                    enter = fadeIn() + slideInVertically(),
                    exit = fadeOut() + slideOutVertically()
                ) {
                    FloatingActionButton(
                        onClick = onAddClick
                    ) {
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = "Add Note"
                        )
                    }
                }
            }
        ) { padding ->
            if (state.notes.isEmpty()) {
                EmptyHome(
                    modifier = Modifier.padding(padding)
                )
            } else {
                NotesList(
                    state = state,
                    padding = padding,
                    listState = listState,
                    onNoteClick = { noteId ->
                        if (state.isSelectionMode) {
                            viewModel.onSelectionClick(noteId)
                        } else {
                            onNoteClick(noteId)
                        }
                    },
                    onNoteLongClick = { noteId ->
                        viewModel.onNoteLongClick(noteId)
                    },
                    onSelectionClick = { noteId ->
                        viewModel.onSelectionClick(noteId)
                    },
                )
            }
        }
        DeleteNotesDialog(
            visible = state.showDeleteDialog,
            selectedCount = state.selectedNotes.size,
            onDismiss = {
                viewModel.hideDeleteDialog()
            },
            onConfirm = {
                viewModel.deleteSelectedNotes()
            }
        )
    }
}