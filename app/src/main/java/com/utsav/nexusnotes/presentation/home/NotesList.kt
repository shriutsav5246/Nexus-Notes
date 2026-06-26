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

@Composable
fun NotesList(

    state: HomeUiState,

    padding: PaddingValues,

    listState: LazyListState,

    onNoteClick: (Long) -> Unit,

    onNoteLongClick: (Long) -> Unit = {},

    onSelectionClick: (Long) -> Unit = {}

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