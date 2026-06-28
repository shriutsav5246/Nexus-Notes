package com.utsav.nexusnotes.presentation.settings.security

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SetPinScreen(

    onBack: () -> Unit,

    onPinSaved: () -> Unit,

    viewModel: SecurityViewModel = hiltViewModel()

) {

    var pin by remember {

        mutableStateOf("")

    }

    var confirmPin by remember {

        mutableStateOf("")

    }

    var error by remember {

        mutableStateOf("")

    }

    Scaffold(

        topBar = {

            CenterAlignedTopAppBar(

                title = {

                    Text("Set PIN")

                },

                navigationIcon = {

                    IconButton(

                        onClick = onBack

                    ) {

                        Icon(

                            imageVector =
                                Icons.AutoMirrored.Filled.ArrowBack,

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

            Text(

                "Create a 4-digit PIN"

            )

            OutlinedTextField(

                value = pin,

                onValueChange = {

                    if (it.length <= 4)

                        pin = it

                },

                label = {

                    Text("PIN")

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

                    Text("Confirm PIN")

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

                    when {

                        pin.length != 4 ->

                            error = "PIN must contain exactly 4 digits."

                        pin != confirmPin ->

                            error = "PINs do not match."

                        else -> {

                            viewModel.savePin(pin)

                            onPinSaved()

                        }

                    }

                }

            ) {

                Text("Save PIN")

            }

        }

    }

}