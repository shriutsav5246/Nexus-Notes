package com.utsav.nexusnotes.presentation.settings.security

data class SecurityUiState(

    val hasPin: Boolean = false,

    val biometricEnabled: Boolean = false,

    val biometricAvailable: Boolean = false,

    val isLoading: Boolean = false

)