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
fun ChangePinScreen(

    onBack: () -> Unit,

    onPinChanged: () -> Unit,

    viewModel: SecurityViewModel = hiltViewModel()

) {

    var currentPin by remember { mutableStateOf("") }

    var newPin by remember { mutableStateOf("") }

    var confirmPin by remember { mutableStateOf("") }

    var error by remember { mutableStateOf("") }

    val scope = rememberCoroutineScope()

    Scaffold(

        topBar = {

            CenterAlignedTopAppBar(

                title = {

                    Text("Change PIN")

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

    ) { padding ->

        Column(

            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(20.dp),

            verticalArrangement = Arrangement.spacedBy(16.dp)

        ) {

            OutlinedTextField(

                value = currentPin,

                onValueChange = {

                    if (it.length <= 4)

                        currentPin = it

                },

                label = {

                    Text("Current PIN")

                },

                keyboardOptions = KeyboardOptions(

                    keyboardType = KeyboardType.NumberPassword

                )

            )

            OutlinedTextField(

                value = newPin,

                onValueChange = {

                    if (it.length <= 4)

                        newPin = it

                },

                label = {

                    Text("New PIN")

                },

                keyboardOptions = KeyboardOptions(

                    keyboardType = KeyboardType.NumberPassword

                )

            )

            OutlinedTextField(

                value = confirmPin,

                onValueChange = {

                    if (it.length <= 4)

                        confirmPin = it

                },

                label = {

                    Text("Confirm New PIN")

                },

                keyboardOptions = KeyboardOptions(

                    keyboardType = KeyboardType.NumberPassword

                )

            )
            if (error.isNotEmpty()) {
                Text(error)
            }
            Button(
                onClick = {
                    scope.launch {
                        if (!viewModel.verifyPin(currentPin)) {
                            error = "Current PIN is incorrect."
                            return@launch

                        }
                        if (newPin.length != 4) {
                            error = "PIN must contain exactly 4 digits."
                            return@launch
                        }
                        if (newPin != confirmPin) {

                            error = "PINs do not match."

                            return@launch

                        }

                        viewModel.savePin(newPin)

                        onPinChanged()

                    }

                }

            ) {

                Text("Update PIN")

            }

        }

    }

}