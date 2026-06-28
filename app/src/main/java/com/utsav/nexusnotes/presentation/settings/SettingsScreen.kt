package com.utsav.nexusnotes.presentation.settings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Palette
import androidx.compose.material.icons.filled.PrivacyTip
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.utsav.nexusnotes.presentation.settings.components.SettingsItem
import com.utsav.nexusnotes.presentation.settings.components.SettingsSection
import com.utsav.nexusnotes.presentation.settings.components.ThemeDropdown
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.utsav.nexusnotes.presentation.settings.theme.ThemeViewModel
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    onBack: () -> Unit,
    viewModel: ThemeViewModel = hiltViewModel()
) {
    val selectedTheme by viewModel
        .selectedTheme
        .collectAsStateWithLifecycle()
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    androidx.compose.material3.Text("Settings")
                },
                navigationIcon = {
                    IconButton(
                        onClick = onBack
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            SettingsSection(
                title = "Appearance"
            ) {
                ThemeDropdown(
                    selectedTheme = selectedTheme,
                    onThemeSelected = { theme ->
                        viewModel.changeTheme(theme)
                    }
                )
            }
            SettingsSection(
                title = "Security"
            ) {
                SettingsItem(
                    title = "PIN & Biometrics",
                    subtitle = "Manage note protection",
                    onClick = { }
                )
            }
            SettingsSection(
                title = "Privacy"
            ) {
                SettingsItem(
                    title = "Privacy Policy",
                    subtitle = "View privacy information",
                    onClick = { }
                )
            }
            SettingsSection(
                title = "About"
            ) {
                SettingsItem(
                    title = "About Nexus Notes",
                    subtitle = "Version 1.0.0",
                    onClick = { }
                )
            }
        }
    }
}