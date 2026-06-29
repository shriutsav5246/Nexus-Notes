package com.utsav.nexusnotes.presentation.home

sealed interface HomeScreenEvent {

    data object ShowUndoSnackbar : HomeScreenEvent

    data object ShareTextNotes : HomeScreenEvent

    data object SharePdfNotes : HomeScreenEvent

}