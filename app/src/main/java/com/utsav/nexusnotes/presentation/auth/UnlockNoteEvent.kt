package com.utsav.nexusnotes.presentation.auth

sealed interface UnlockNoteEvent {

    data class PinChanged(
        val value: String
    ) : UnlockNoteEvent

    data object VerifyPin : UnlockNoteEvent

    data object TryBiometric : UnlockNoteEvent

}