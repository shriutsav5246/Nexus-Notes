package com.utsav.nexusnotes.presentation.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen() {

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
                onClick = { }
            ) {

                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add Note"
                )

            }

        }

    ) { padding ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {

                Text(
                    text = "No Notes Yet",
                    style = MaterialTheme.typography.headlineSmall
                )

                Text(
                    modifier = Modifier.padding(top = 8.dp),
                    text = "Tap the + button to create your first note.",
                    style = MaterialTheme.typography.bodyMedium
                )

            }

        }

    }

}