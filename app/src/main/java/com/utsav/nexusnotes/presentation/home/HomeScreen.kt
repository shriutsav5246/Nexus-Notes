package com.utsav.nexusnotes.presentation.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(

    onAddClick: () -> Unit,

    onNoteClick: (Long) -> Unit,

    viewModel: HomeViewModel = hiltViewModel()

) {

    val state by viewModel.state.collectAsStateWithLifecycle()

    Scaffold(

        topBar = {

            CenterAlignedTopAppBar(

                title = {
                    Text("Nexus Notes")
                },

                actions = {

                    IconButton(onClick = { }) {

                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "Search"
                        )

                    }

                    IconButton(onClick = { }) {

                        Icon(
                            imageVector = Icons.Default.Settings,
                            contentDescription = "Settings"
                        )

                    }

                },

                colors = TopAppBarDefaults.centerAlignedTopAppBarColors()

            )

        },

        floatingActionButton = {

            FloatingActionButton(

                onClick = onAddClick

            ) {

                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add Note"
                )

            }

        }

    ) { padding ->

        if (state.notes.isEmpty()) {

            EmptyHome(
                modifier = Modifier.padding(padding)
            )

        } else {

            NotesGrid(
                state = state,
                padding = padding,
                onNoteClick = onNoteClick
            )

        }

    }

}

@Composable
private fun EmptyHome(
    modifier: Modifier = Modifier
) {

    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {

        Text(
            text = "No Notes Yet\n\nTap + to create your first note.",
            style = MaterialTheme.typography.bodyLarge
        )

    }

}

@Composable
private fun NotesGrid(
    state: HomeUiState,
    padding: PaddingValues,
    onNoteClick: (Long) -> Unit
) {

    LazyVerticalGrid(

        columns = GridCells.Fixed(2),

        modifier = Modifier
            .fillMaxSize()
            .padding(padding),

        contentPadding = PaddingValues(16.dp),

        horizontalArrangement = Arrangement.spacedBy(12.dp),

        verticalArrangement = Arrangement.spacedBy(12.dp)

    ) {

        items(state.notes) { note ->

            Card(

                onClick = {

                    onNoteClick(note.id)

                },

                elevation = CardDefaults.cardElevation(
                    defaultElevation = 2.dp
                )

            ) {

                androidx.compose.foundation.layout.Column(
                    modifier = Modifier.padding(16.dp)
                ) {

                    Text(
                        text = note.title,
                        style = MaterialTheme.typography.titleMedium,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )

                    Text(
                        modifier = Modifier.padding(top = 8.dp),
                        text = note.content,
                        style = MaterialTheme.typography.bodyMedium,
                        maxLines = 6,
                        overflow = TextOverflow.Ellipsis
                    )

                }

            }

        }

    }

}