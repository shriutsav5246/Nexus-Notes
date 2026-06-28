package com.utsav.nexusnotes.core.security

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.pinDataStore by preferencesDataStore(
    name = "security_settings"
)

object PinPreferences {

    private val PIN_HASH =
        stringPreferencesKey("pin_hash")

    private val BIOMETRIC_ENABLED =
        booleanPreferencesKey("biometric_enabled")

    fun getPinHash(
        context: Context
    ): Flow<String> {

        return context.pinDataStore.data.map {

            it[PIN_HASH] ?: ""

        }

    }

    suspend fun savePinHash(

        context: Context,

        hash: String

    ) {

        context.pinDataStore.edit {

            it[PIN_HASH] = hash

        }

    }

    fun biometricEnabled(

        context: Context

    ): Flow<Boolean> {

        return context.pinDataStore.data.map {

            it[BIOMETRIC_ENABLED] ?: false

        }

    }

    suspend fun setBiometricEnabled(

        context: Context,

        enabled: Boolean

    ) {

        context.pinDataStore.edit {

            it[BIOMETRIC_ENABLED] = enabled

        }

    }

}