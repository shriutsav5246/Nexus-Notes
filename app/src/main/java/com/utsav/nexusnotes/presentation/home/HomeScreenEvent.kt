package com.utsav.nexusnotes.presentation.home

sealed interface HomeScreenEvent {

    data object ShowUndoSnackbar : HomeScreenEvent

}