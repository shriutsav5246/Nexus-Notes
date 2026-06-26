package com.utsav.nexusnotes.presentation.editor

sealed interface EditorUiEvent {

    data class TitleChanged(
        val value: String
    ) : EditorUiEvent

    data class ContentChanged(
        val value: String
    ) : EditorUiEvent

    data object BackPressed : EditorUiEvent

}