package com.utsav.nexusnotes.presentation.settings.security

sealed interface SecurityEvent {

    data object EnableBiometric : SecurityEvent

    data object DisableBiometric : SecurityEvent

    data object SetPin : SecurityEvent

    data object ChangePin : SecurityEvent

    data object RemovePin : SecurityEvent

}