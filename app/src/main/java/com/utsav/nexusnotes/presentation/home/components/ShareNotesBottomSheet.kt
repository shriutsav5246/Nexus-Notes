package com.utsav.nexusnotes.presentation.home.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.PictureAsPdf
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.clickable

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShareNotesBottomSheet(
    onDismiss: () -> Unit,
    onSharePdf: () -> Unit,
    onShareText: () -> Unit
) {
    ModalBottomSheet(
        onDismissRequest = onDismiss
    ) {
        Text(
            text = "Share Notes As",
            modifier = Modifier.padding(
                horizontal = 24.dp,
                vertical = 8.dp
            )
        )
        ListItem(
            headlineContent = {
                Text("PDF")
            },
            supportingContent = {
                Text("Share selected notes as PDF")
            },
            leadingContent = {
                Icon(
                    imageVector = Icons.Default.PictureAsPdf,
                    contentDescription = null
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    onSharePdf()
                }
        )
        ListItem(
            headlineContent = {
                Text("Text")
            },
            supportingContent = {
                Text("Share selected notes as TXT")
            },
            leadingContent = {
                Icon(
                    imageVector = Icons.Default.Description,
                    contentDescription = null
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    onShareText()
                }
        )

        TextButton(

            onClick = onDismiss,

            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)

        ) {

            Text("Cancel")

        }

    }

}