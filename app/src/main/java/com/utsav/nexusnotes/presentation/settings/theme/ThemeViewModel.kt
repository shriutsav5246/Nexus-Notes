package com.utsav.nexusnotes.presentation.settings.theme

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.utsav.nexusnotes.core.preferences.ThemePreferences
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ThemeViewModel @Inject constructor(
    application: Application
) : AndroidViewModel(application) {

    val selectedTheme: StateFlow<String> =
        ThemePreferences
            .getTheme(getApplication())
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = "System"
            )

    fun changeTheme(
        theme: String
    ) {

        viewModelScope.launch {

            ThemePreferences.saveTheme(
                getApplication(),
                theme
            )

        }

    }

}