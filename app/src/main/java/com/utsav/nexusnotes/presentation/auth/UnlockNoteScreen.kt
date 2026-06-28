package com.utsav.nexusnotes.presentation.auth

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun UnlockNoteScreen(

    noteId: Long,

    onSuccess: () -> Unit,

    onBack: () -> Unit,

    viewModel: UnlockNoteViewModel = hiltViewModel()

) {

    Text("Unlock Note")

}