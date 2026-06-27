package com.utsav.nexusnotes.presentation.trash.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable

@Composable
fun RestoreNoteDialog(

    visible: Boolean,

    onDismiss: () -> Unit,

    onConfirm: () -> Unit

) {

    if (!visible) return

    AlertDialog(

        onDismissRequest = onDismiss,

        title = {
            Text("Restore Note")
        },

        text = {
            Text("Do you want to restore this note?")
        },

        confirmButton = {

            TextButton(
                onClick = onConfirm
            ) {
                Text("Restore")
            }

        },

        dismissButton = {

            TextButton(
                onClick = onDismiss
            ) {
                Text("Cancel")
            }

        }

    )

}