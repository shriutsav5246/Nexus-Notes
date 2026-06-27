package com.utsav.nexusnotes.presentation.home.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.utsav.nexusnotes.presentation.home.HomeUiState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.ui.Alignment
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height

@Composable
fun NotesList(

    state: HomeUiState,

    padding: PaddingValues,

    listState: LazyListState,

    onNoteClick: (Long) -> Unit,

    onNoteLongClick: (Long) -> Unit = {},

    onSelectionClick: (Long) -> Unit = {},

    onSwipeDelete: (Long) -> Unit = {}

) {

    LazyColumn(

        state = listState,

        modifier = Modifier
            .fillMaxSize()
            .padding(padding),

        contentPadding = PaddingValues(
            start = 8.dp,
            end = 8.dp,
            top = 12.dp,
            bottom = 100.dp
        )

    ) {

        items(

            items = state.notes,

            key = { it.id }

        ) { note ->

            val dismissState = rememberSwipeToDismissBoxState(

                confirmValueChange = { value ->

                    if (

                        value == SwipeToDismissBoxValue.EndToStart &&
                        !state.isSelectionMode

                    ) {

                        onSwipeDelete(note.id)

                    }

                    false

                }

            )

            SwipeToDismissBox(

                state = dismissState,

                enableDismissFromStartToEnd = false,

                enableDismissFromEndToStart = !state.isSelectionMode,

                backgroundContent = {

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 8.dp)
                            .height(110.dp)
                            .background(
                                color = MaterialTheme.colorScheme.errorContainer,
                                shape = MaterialTheme.shapes.large
                            ),
                        contentAlignment = Alignment.CenterEnd
                    ) {

                        Icon(
                            imageVector = Icons.Default.Delete,
                            contentDescription = null,
                            modifier = Modifier.padding(end = 24.dp),
                            tint = MaterialTheme.colorScheme.onErrorContainer
                        )

                    }

                }

            ) {

                NoteListItem(

                    note = note,

                    selected = note.id in state.selectedNotes,

                    onClick = {

                        if (state.isSelectionMode) {

                            onSelectionClick(note.id)

                        } else {

                            onNoteClick(note.id)

                        }

                    },

                    onLongClick = {

                        onNoteLongClick(note.id)

                    }

                )

            }

        }

    }

}