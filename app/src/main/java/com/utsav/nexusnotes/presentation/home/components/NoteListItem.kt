package com.utsav.nexusnotes.presentation.home.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.utsav.nexusnotes.core.utils.toRelativeTime
import com.utsav.nexusnotes.domain.model.Note

@Composable
fun NoteListItem(

    note: Note,

    selected: Boolean,

    onClick: () -> Unit,

    onLongClick: () -> Unit

) {

    Box(

        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .combinedClickable(
                onClick = onClick,
                onLongClick = onLongClick
            )

    ) {

        Card(

            modifier = Modifier.fillMaxWidth(),

            shape = MaterialTheme.shapes.large,

            border =
                if (selected)
                    BorderStroke(
                        2.dp,
                        MaterialTheme.colorScheme.primary
                    )
                else
                    null,

            elevation = CardDefaults.cardElevation(
                defaultElevation =
                    if (selected) 8.dp else 4.dp
            ),

            colors = CardDefaults.cardColors(
                containerColor =
                    if (selected)
                        MaterialTheme.colorScheme.primaryContainer
                    else
                        MaterialTheme.colorScheme.surfaceContainerLow
            )

        ) {

            Column(

                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)

            ) {

                Text(

                    text = note.title,

                    style = MaterialTheme.typography.titleLarge,

                    fontWeight = FontWeight.SemiBold,

                    maxLines = 1,

                    overflow = TextOverflow.Ellipsis

                )

                Spacer(
                    modifier = Modifier.height(14.dp)
                )

                Text(

                    text = "Last edited ${note.updatedAt.toRelativeTime()}",

                    style = MaterialTheme.typography.bodySmall,

                    color = MaterialTheme.colorScheme.outline

                )

            }

        }

        if (selected) {

            Surface(

                modifier = Modifier
                    .padding(12.dp)
                    .align(Alignment.TopEnd),

                shape = CircleShape,

                color = MaterialTheme.colorScheme.primary

            ) {

                Icon(

                    imageVector = Icons.Default.Check,

                    contentDescription = null,

                    tint = MaterialTheme.colorScheme.onPrimary,

                    modifier = Modifier.padding(4.dp)

                )

            }

        }

    }

}