package com.utsav.nexusnotes.presentation.home.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable

@Composable
fun DeleteNotesDialog(

    visible: Boolean,

    selectedCount: Int,

    onDismiss: () -> Unit,

    onConfirm: () -> Unit

) {

    if (!visible) return

    AlertDialog(

        onDismissRequest = onDismiss,

        title = {

            Text("Delete Notes")

        },

        text = {

            Text(
                "Delete $selectedCount selected note(s)? This action cannot be undone."
            )

        },

        confirmButton = {

            TextButton(

                onClick = onConfirm

            ) {

                Text("Delete")

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