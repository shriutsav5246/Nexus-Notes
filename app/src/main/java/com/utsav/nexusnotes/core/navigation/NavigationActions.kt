package com.utsav.nexusnotes.core.navigation

import androidx.navigation.NavHostController

fun NavHostController.openEditor() {
    openEditor(0L)
}

fun NavHostController.openEditor(noteId: Long) {
    navigate("${Routes.EDITOR}/$noteId")
}

fun NavHostController.goBack() {
    popBackStack()
}