package com.utsav.nexusnotes.core.security

sealed interface BiometricResult {

    data object Success : BiometricResult

    data object Failed : BiometricResult

    data class Error(
        val message: String
    ) : BiometricResult

    data object HardwareUnavailable : BiometricResult

    data object FeatureUnavailable : BiometricResult

    data object AuthenticationNotSet : BiometricResult

    data object Cancelled : BiometricResult
}