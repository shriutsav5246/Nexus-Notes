    package com.utsav.nexusnotes.presentation.home.components

    import androidx.compose.material.icons.Icons
    import androidx.compose.material.icons.automirrored.filled.ArrowBack
    import androidx.compose.material.icons.filled.Check
    import androidx.compose.material.icons.filled.Delete
    import androidx.compose.material.icons.filled.MoreVert
    import androidx.compose.material.icons.filled.Search
    import androidx.compose.material.icons.filled.Settings
    import androidx.compose.material3.CenterAlignedTopAppBar
    import androidx.compose.material3.ExperimentalMaterial3Api
    import androidx.compose.material3.Icon
    import androidx.compose.material3.IconButton
    import androidx.compose.material3.Text
    import androidx.compose.material3.TopAppBarDefaults
    import androidx.compose.material.icons.filled.Menu
    import androidx.compose.runtime.Composable
    import androidx.compose.material.icons.filled.Share

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun HomeTopBar(
        isSelectionMode: Boolean,
        selectedCount: Int,
        allSelected: Boolean,
        onBackClick: () -> Unit,
        onMenuClick: () -> Unit,
        onSearchClick: () -> Unit,
        onSettingsClick: () -> Unit,
        onDeleteClick: () -> Unit,
        onShareClick: () -> Unit,
        onSelectAllClick: () -> Unit,
        onMoreClick: () -> Unit = {}
    ) {
        if (isSelectionMode) {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "$selectedCount selected"
                    )
                },
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
                        onClick = onDeleteClick
                    ) {
                        Icon(
                            imageVector = Icons.Default.Delete,
                            contentDescription = "Delete Selected"
                        )
                    }
                    IconButton(
                        onClick = onShareClick
                    ) {
                        Icon(
                            imageVector = Icons.Default.Share,
                            contentDescription = "Share Selected"
                        )
                    }
                    IconButton(
                        onClick = onSelectAllClick
                    ) {
                        Icon(
                            imageVector = Icons.Default.Check,
                            contentDescription =
                                if (allSelected)
                                    "Deselect All"
                                else
                                    "Select All"
                        )
                    }
                    IconButton(
                        onClick = onMoreClick
                    ) {
                        Icon(
                            imageVector = Icons.Default.MoreVert,
                            contentDescription = "More Options"
                        )
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors()
            )
        } else {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Nexus Notes"
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = onMenuClick
                    ) {
                        Icon(
                            imageVector = Icons.Default.Menu,
                            contentDescription = "Navigation Menu"
                        )
                    }
                },
                actions = {
                    IconButton(
                        onClick = onSearchClick
                    ) {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "Search"
                        )
                    }
                    IconButton(
                        onClick = onSettingsClick
                    ) {
                        Icon(
                            imageVector = Icons.Default.Settings,
                            contentDescription = "Settings"
                        )
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors()
            )
        }
    }