package com.utsav.nexusnotes.core.navigation
import com.utsav.nexusnotes.presentation.settings.SettingsScreen
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.utsav.nexusnotes.presentation.editor.EditorScreen
import com.utsav.nexusnotes.presentation.home.HomeScreen
import com.utsav.nexusnotes.presentation.trash.TrashScreen
@Composable
fun AppNavHost() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Routes.HOME
    )
    {
        composable(
            route = Routes.HOME
        ) {
            HomeScreen(
                onAddClick = {
                    navController.openEditor()
                },
                onNoteClick = { noteId ->
                    navController.openEditor(noteId)
                },
                onTrashClick = {
                    navController.navigate(Routes.TRASH)
                },
                onSettingsClick = {
                    navController.navigate(Routes.SETTINGS)
                },
                onAboutClick = {
                    // TODO
                }
            )
        }
        composable(
            route = Routes.EDITOR_ROUTE,
            arguments = listOf(
                navArgument(NavArguments.NOTE_ID) {
                    type = NavType.LongType
                    defaultValue = 0L
                }
            )
        )
        {
            val noteId =
                it.arguments?.getLong(
                    NavArguments.NOTE_ID
                ) ?: 0L
            EditorScreen(
                noteId = noteId,
                onBack = {
                    navController.goBack()
                }
            )
        }
        composable(
            route = Routes.TRASH
        ) {

            TrashScreen(

                onBack = {

                    navController.goBack()

                }

            )

        }
        composable(
            route = Routes.SETTINGS
        ) {

            SettingsScreen(
                onBack = {
                    navController.goBack()
                }
            )

        }
    }

}