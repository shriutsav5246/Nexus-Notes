package com.utsav.nexusnotes.presentation.about

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AboutAppScreen(
    onBack: () -> Unit
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text("About This App")
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
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                elevation = CardDefaults.cardElevation(2.dp)
            ) {
                Column(
                    modifier = Modifier.padding(20.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Info,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary
                    )
                    Text(
                        text = "Nexus Notes",
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "Built with using Kotlin & Jetpack Compose",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
            AboutSection(
                "Developer",
                "Hi! I'm Utsav Shrivastav, a B.Tech Computer Science student passionate about Android development, modern UI design, and building practical applications."
            )
            AboutSection(
                "Development Journey",
                "Nexus Notes is my flagship Android project. I started building it to learn Clean Architecture, MVVM, Room, Hilt, DataStore, Material 3 and modern Android development while creating something useful."
            )
            AboutSection(
                "Why Nexus Notes?",
                "Most note apps either feel cluttered or collect unnecessary user data. My goal is to build a clean, privacy-focused and elegant note-taking experience."
            )
            AboutSection(
                "Future Roadmap",
                "• Rich Text Editor\n• Cloud Backup\n• Widgets\n• OCR\n• PDF Export\n• Cross-device Sync"
            )
            AboutSection(
                "Thank You",
                "Thank you for using Nexus Notes and being part of this journey. Every new feature is built with care to make the app faster, cleaner and more useful."
            )
            Text(
                text = "Version 1.0.0",
                modifier = Modifier.padding(bottom = 20.dp),
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.outline
            )
        }
    }
}
@Composable
private fun AboutSection(
    title: String,
    body: String
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold
            )
            HorizontalDivider(
                modifier = Modifier.padding(vertical = 12.dp)
            )
            Text(
                text = body,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}