package com.utsav.nexusnotes.presentation.settings.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun SettingsItem(

    title: String,

    subtitle: String? = null,

    onClick: () -> Unit

) {

    Column(

        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onClick()
            }

    ) {

        Row(

            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = 20.dp,
                    vertical = 16.dp
                ),

            horizontalArrangement = Arrangement.SpaceBetween,

            verticalAlignment = Alignment.CenterVertically

        ) {

            Column {

                Text(

                    text = title,

                    style = MaterialTheme.typography.bodyLarge,

                    fontWeight = FontWeight.Medium

                )

                subtitle?.let {

                    Text(

                        text = it,

                        style = MaterialTheme.typography.bodySmall,

                        color = MaterialTheme.colorScheme.outline,

                        modifier = Modifier.padding(top = 2.dp)

                    )

                }

            }

            Icon(

                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,

                contentDescription = null,

                tint = MaterialTheme.colorScheme.outline

            )

        }

        HorizontalDivider()

    }

}