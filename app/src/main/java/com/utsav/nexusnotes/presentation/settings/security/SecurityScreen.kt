package com.utsav.nexusnotes.presentation.settings.security

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.ui.platform.LocalContext
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SecurityScreen(

    onBack: () -> Unit,

    onSetPinClick: () -> Unit,

    onChangePinClick: () -> Unit = {},

    onDisablePinClick: () -> Unit = {},

    viewModel: SecurityViewModel = hiltViewModel()

) {

    val hasPin by viewModel
        .hasPin
        .collectAsStateWithLifecycle()

    val biometricEnabled by viewModel
        .biometricEnabled
        .collectAsStateWithLifecycle()
    val context = LocalContext.current

    val activity = context as? FragmentActivity

    val biometricAuthenticator =
        activity?.let {
            BiometricAuthenticator(it)
        }

    Scaffold(

        topBar = {

            CenterAlignedTopAppBar(

                title = {

                    Text("PIN & Biometrics")

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
                .padding(20.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            Text(
                text = "PIN Status",
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text =
                    if (hasPin)
                        "Protected"
                    else
                        "Not Protected"
            )
            if (hasPin) {
                Button(
                    onClick = onChangePinClick
                ) {
                    Text("Change PIN")
                }
                Button(
                    onClick = onDisablePinClick
                ) {
                    Text("Disable PIN")
                }
            } else {
                Button(
                    onClick = onSetPinClick
                ) {
                    Text("Set PIN")
                }
            }
            Text(
                text = "Biometric Authentication",
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text =
                    if (biometricEnabled)
                        "Enabled"
                    else
                        "Disabled"
            )
            if (hasPin) {

            }

            Text(

                text = "Security",

                style = MaterialTheme.typography.titleMedium

            )

            Text(

                text =
                    "• Your PIN is securely hashed.\n" +
                            "• Biometrics use Android's secure authentication.\n" +
                            "• Locked notes require authentication before opening."

            )

        }

    }

}