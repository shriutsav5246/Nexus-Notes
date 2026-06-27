package com.utsav.nexusnotes.presentation.trash.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Restore
import androidx.compose.material.icons.filled.DeleteForever
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.material.icons.filled.Delete

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TrashTopBar(
    isSelectionMode: Boolean,
    selectedCount: Int,
    onBackClick: () -> Unit,
    onRestoreClick: () -> Unit,
    onDeleteClick: () -> Unit,
    onDeleteAllClick: () -> Unit
) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                if (isSelectionMode)
                    "$selectedCount Selected"
                else
                    "Trash"
            )
        },
        navigationIcon = {
            IconButton(
                onClick = onBackClick
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = null
                )
            }
        },
        actions = {

            if (isSelectionMode) {

                IconButton(
                    onClick = onRestoreClick
                ) {
                    Icon(
                        imageVector = Icons.Default.Restore,
                        contentDescription = "Restore"
                    )
                }

                IconButton(
                    onClick = onDeleteClick
                ) {
                    Icon(
                        imageVector = Icons.Default.DeleteForever,
                        contentDescription = "Delete Selected"
                    )
                }

            } else {

                IconButton(
                    onClick = onDeleteAllClick
                ) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Delete All"
                    )
                }

            }

        }
    )
}