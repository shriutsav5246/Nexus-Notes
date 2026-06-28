package com.utsav.nexusnotes.presentation.settings.security

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import kotlinx.coroutines.launch
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VerifyPinScreen(
    title: String,
    onBack: () -> Unit,
    onSuccess: () -> Unit,
    showDisableDialog: Boolean = false,
    viewModel: SecurityViewModel = hiltViewModel()
) {
    var pin by remember {
        mutableStateOf("")
    }
    var error by remember {
        mutableStateOf("")
    }
    var showDialog by remember {
        mutableStateOf(false)
    }
    val scope = rememberCoroutineScope()
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(title)
                },
                navigationIcon = {
                    IconButton(
                        onClick = onBack
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = null
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
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text("Enter your PIN")
            OutlinedTextField(
                value = pin,
                onValueChange = {
                    if (it.length <= 4)
                        pin = it
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.NumberPassword
                ),
                label = {
                    Text("PIN")
                }
            )
            if (error.isNotEmpty()) {
                Text(error)
            }
            Button(
                onClick = {
                    scope.launch {
                        if (viewModel.verifyPin(pin)) {

                            if (showDisableDialog) {

                                showDialog = true

                            } else {

                                onSuccess()

                            }

                        } else {

                            error = "Incorrect PIN"

                        }
                    }
                }
            ) {
                Text("Verify")
            }
        }
    }
    if (showDialog) {

        androidx.compose.material3.AlertDialog(

            onDismissRequest = {

                showDialog = false

            },

            title = {

                Text("Disable PIN")

            },

            text = {

                Text(
                    "Are you sure you want to remove your PIN protection?"
                )

            },

            confirmButton = {

                Button(

                    onClick = {

                        viewModel.removePin()

                        showDialog = false

                        onSuccess()

                    }

                ) {

                    Text("Disable")

                }

            },

            dismissButton = {

                Button(

                    onClick = {

                        showDialog = false

                    }

                ) {

                    Text("Cancel")

                }

            }

        )
    }
}