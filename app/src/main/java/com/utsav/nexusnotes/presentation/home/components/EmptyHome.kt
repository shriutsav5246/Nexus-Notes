package com.utsav.nexusnotes.presentation.home.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun EmptyHome(
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