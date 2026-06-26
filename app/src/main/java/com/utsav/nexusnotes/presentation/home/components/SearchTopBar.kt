package com.utsav.nexusnotes.presentation.home.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SearchTopBar(

    query: String,

    onQueryChange: (String) -> Unit,

    onCloseClick: () -> Unit

) {

    OutlinedTextField(

        value = query,

        onValueChange = onQueryChange,

        modifier = Modifier
            .fillMaxWidth()
            .statusBarsPadding()
            .padding(horizontal = 12.dp, vertical = 8.dp),

        singleLine = true,

        placeholder = {

            Text("Search notes")

        },

        leadingIcon = {

            Icon(

                imageVector = Icons.Default.Search,

                contentDescription = null

            )

        },

        trailingIcon = {

            IconButton(

                onClick = onCloseClick

            ) {

                Icon(

                    imageVector = Icons.Default.Close,

                    contentDescription = "Close Search"

                )

            }

        },

        shape = MaterialTheme.shapes.extraLarge

    )

}