package com.utsav.nexusnotes.presentation.editor.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.LockOpen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditorTopBar(
    onBackClick: () -> Unit,
    onMoreClick: () -> Unit = {},
    isLocked: Boolean = false,
    onLockClick: () -> Unit = {}

) {

    CenterAlignedTopAppBar(

        title = {},

        navigationIcon = {

            IconButton(

                onClick = onBackClick

            ) {

                Icon(

                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,

                    contentDescription = "Back"

                )

            }

        },

        actions = {

            IconButton(
                onClick = onLockClick
            ) {

                Icon(

                    imageVector =
                        if (isLocked)
                            Icons.Default.LockOpen
                        else
                            Icons.Default.Lock,

                    contentDescription =
                        if (isLocked)
                            "Unlock Note"
                        else
                            "Lock Note"

                )

            }

            IconButton(

                onClick = onMoreClick

            ) {

                Icon(

                    imageVector = Icons.Default.MoreVert,

                    contentDescription = "More"

                )

            }

        },

        colors = TopAppBarDefaults.centerAlignedTopAppBarColors()

    )

}