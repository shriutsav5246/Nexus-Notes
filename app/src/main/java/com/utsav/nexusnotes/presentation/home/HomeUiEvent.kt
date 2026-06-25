package com.utsav.nexusnotes.presentation.home

sealed interface HomeUiEvent {

    data object CreateNote : HomeUiEvent

    data object Search : HomeUiEvent

    data object Settings : HomeUiEvent

}