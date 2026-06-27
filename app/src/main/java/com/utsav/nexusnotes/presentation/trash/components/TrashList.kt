package com.utsav.nexusnotes.presentation.trash.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.utsav.nexusnotes.domain.model.Note
import com.utsav.nexusnotes.presentation.home.components.NoteListItem

@Composable
fun TrashList(
    notes: List<Note>,
    padding: PaddingValues,
    selectedNotes: Set<Long>,
    isSelectionMode: Boolean,
    onNoteClick: (Long) -> Unit,
    onNoteLongClick: (Long) -> Unit,
    onSelectionClick: (Long) -> Unit
) {

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding),
        contentPadding = PaddingValues(
            top = 12.dp,
            bottom = 100.dp
        )
    ) {

        items(
            items = notes,
            key = { note -> note.id }
        ) { note ->

            NoteListItem(

                note = note,

                selected = note.id in selectedNotes,

                onClick = {

                    if (isSelectionMode) {

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