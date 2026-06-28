package com.utsav.nexusnotes.presentation.settings.privacy

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
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
fun PrivacyPolicyScreen(
    onBack: () -> Unit
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text("Privacy Policy")
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
    )
    { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        )
        {
            Text(
                text = "Last Updated: 29 June 2026",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.outline
            )
            PrivacyCard(
                title = "Information We Store",
                body =
                    "• Notes\n" +
                            "• Theme Preference\n" +
                            "• PIN Hash\n" +
                            "• Biometric Preference"
            )
            PrivacyCard(
                title = "Security",
                body =
                    "• PINs are securely hashed.\n" +
                            "• Biometric authentication is handled by Android.\n" +
                            "• Your notes remain stored locally on your device."
            )
            PrivacyCard(
                title = "Permissions",
                body =
                    "Biometrics\n" +
                            "Storage"
            )
            PrivacyCard(
                title = "Third-Party Services",
                body =
                    "Nexus Notes currently does not share your data with any third-party services."
            )
            PrivacyCard(
                title = "Contact",
                body =
                    "support@nexusnotes.app"
            )
        }
    }
}
@Composable
private fun PrivacyCard(
    title: String,
    body: String
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        )
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