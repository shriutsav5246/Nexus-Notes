package com.utsav.nexusnotes.presentation.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerDefaults
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.utsav.nexusnotes.R
@Composable
fun HomeDrawer(
    isHomeSelected: Boolean,
    isTrashSelected: Boolean,
    onHomeClick: () -> Unit,
    onTrashClick: () -> Unit,
    onAboutClick: () -> Unit
) {
    ModalDrawerSheet(
        drawerContainerColor = DrawerDefaults.containerColor
    ) {
        Image(
            painter = painterResource(R.drawable.drawer_header),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.height(8.dp))
        NavigationDrawerItem(
            label = {
                Text("Home")
            },
            selected = isHomeSelected,
            onClick = onHomeClick,
            icon = {
                androidx.compose.material3.Icon(
                    Icons.Default.Home,
                    null
                )
            },
            modifier = Modifier.padding(
                NavigationDrawerItemDefaults.ItemPadding
            )
        )
        NavigationDrawerItem(
            label = {
                Text("Trash")
            },
            selected = isTrashSelected,
            onClick = onTrashClick,
            icon = {
                androidx.compose.material3.Icon(
                    Icons.Default.Delete,
                    null
                )
            },
            modifier = Modifier.padding(
                NavigationDrawerItemDefaults.ItemPadding
            )
        )
        NavigationDrawerItem(
            label = {
                Text("About")
            },
            selected = false,
            onClick = onAboutClick,
            icon = {
                androidx.compose.material3.Icon(
                    Icons.Default.Info,
                    null
                )
            },
            modifier = Modifier.padding(
                NavigationDrawerItemDefaults.ItemPadding
            )
        )
//        Spacer(modifier = Modifier.weight(1f, fill = true))
        Spacer(modifier = Modifier.height(24.dp))
        Divider()
        Text(
            text = "Version 1.0.0",
            modifier = Modifier.padding(16.dp)
        )
    }
}