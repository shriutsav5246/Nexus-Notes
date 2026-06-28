package com.utsav.nexusnotes.presentation.settings.security

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.utsav.nexusnotes.core.security.PinHasher
import com.utsav.nexusnotes.core.security.PinPreferences
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SecurityViewModel @Inject constructor(
    application: Application
) : AndroidViewModel(application) {

    private val context = getApplication<Application>()

    val hasPin: StateFlow<Boolean> =
        PinPreferences.getPinHash(context)
            .combine(
                PinPreferences.biometricEnabled(context)
            ) { hash, _ ->
                hash.isNotBlank()
            }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = false
            )

    val biometricEnabled: StateFlow<Boolean> =
        PinPreferences
            .biometricEnabled(context)
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = false
            )

    fun savePin(
        pin: String
    ) {

        viewModelScope.launch {

            PinPreferences.savePinHash(
                context,
                PinHasher.hash(pin)
            )

        }

    }

    fun removePin() {

        viewModelScope.launch {

            PinPreferences.savePinHash(
                context,
                ""
            )

            PinPreferences.setBiometricEnabled(
                context,
                false
            )

        }

    }

    fun setBiometricEnabled(
        enabled: Boolean
    ) {

        viewModelScope.launch {

            PinPreferences.setBiometricEnabled(
                context,
                enabled
            )

        }

    }

    suspend fun verifyPin(
        pin: String
    ): Boolean {

        val storedHash =

            PinPreferences
                .getPinHash(context)
                .first()

        return PinHasher.verify(

            pin,

            storedHash

        )

    }

}