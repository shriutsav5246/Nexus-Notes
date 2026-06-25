package com.utsav.nexusnotes.core.navigation

import androidx.navigation.NavHostController

fun NavHostController.openEditor() {
    navigate(Routes.EDITOR)
}

fun NavHostController.openEditor(noteId: Long) {
    navigate("editor/$noteId")
}

fun NavHostController.goBack() {
    popBackStack()
}