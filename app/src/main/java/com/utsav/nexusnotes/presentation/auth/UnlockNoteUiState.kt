package com.utsav.nexusnotes.presentation.auth

data class UnlockNoteUiState(

    val noteId: Long = 0L,

    val enteredPin: String = "",

    val isLoading: Boolean = false,

    val showPinError: Boolean = false,

    val biometricAvailable: Boolean = false,

    val biometricEnabled: Boolean = false

)