package com.utsav.nexusnotes.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.utsav.nexusnotes.presentation.editor.EditorScreen
import com.utsav.nexusnotes.presentation.home.HomeScreen

@Composable
fun AppNavHost() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.HOME
    ) {

        composable(Routes.HOME) {

            HomeScreen(

                onAddClick = {

                    navController.navigate(Routes.EDITOR)

                },

                onNoteClick = { noteId ->

                    navController.navigate(
                        "editor/$noteId"
                    )

                }

            )

        }

        composable(
            route = Routes.EDITOR
        ) {

            EditorScreen(

                onBack = {

                    navController.popBackStack()

                }

            )

        }

        composable(

            route = Routes.EDITOR_WITH_ID,

            arguments = listOf(

                navArgument(
                    NavArguments.NOTE_ID
                ) {
                    type = NavType.LongType
                }

            )

        ) {

            EditorScreen(

                onBack = {

                    navController.popBackStack()

                }

            )

        }

    }

}