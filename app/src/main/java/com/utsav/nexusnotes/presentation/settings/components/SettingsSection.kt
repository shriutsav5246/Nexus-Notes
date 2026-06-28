package com.utsav.nexusnotes.presentation.settings.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun SettingsSection(

    title: String,

    content: @Composable () -> Unit

) {

    Column {

        Text(

            text = title,

            modifier = Modifier.padding(
                start = 20.dp,
                top = 24.dp,
                bottom = 8.dp
            ),

            style = MaterialTheme.typography.titleMedium,

            fontWeight = FontWeight.SemiBold,

            color = MaterialTheme.colorScheme.primary

        )

        content()

    }

}