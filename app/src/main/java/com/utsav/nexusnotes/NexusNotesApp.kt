package com.utsav.nexusnotes

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.compose.ui.Modifier
import com.utsav.nexusnotes.core.navigation.AppNavHost
import com.utsav.nexusnotes.core.ui.theme.NexusNotesTheme
import com.utsav.nexusnotes.presentation.settings.theme.ThemeViewModel

@Composable
fun NexusNotesApp() {

    val viewModel: ThemeViewModel = hiltViewModel()

    val themeMode by viewModel
        .selectedTheme
        .collectAsStateWithLifecycle()

    val darkTheme = when (themeMode) {
        "Light" -> false
        "Dark" -> true
        else -> isSystemInDarkTheme()
    }

    NexusNotesTheme(
        darkTheme = darkTheme
    ) {

        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {

            AppNavHost()

        }

    }

}