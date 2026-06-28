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
import com.utsav.nexusnotes.presentation.settings.security.SecurityScreen
import com.utsav.nexusnotes.presentation.settings.security.SetPinScreen
import com.utsav.nexusnotes.presentation.settings.security.ChangePinScreen
import com.utsav.nexusnotes.presentation.settings.security.VerifyPinScreen
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
                },
                onSecurityClick = {
                    navController.navigate(
                        Routes.SECURITY
                    )
                }
            )
        }
        composable(
            route = Routes.SECURITY
        ) {
            SecurityScreen(
                onBack = {
                    navController.goBack()
                },

                onSetPinClick = {
                    navController.navigate(
                        Routes.SET_PIN
                    )
                },

                onChangePinClick = {
                    navController.navigate(
                        Routes.CHANGE_PIN
                    )
                },

                onDisablePinClick = {
                    navController.navigate(
                        Routes.VERIFY_PIN
                    )
                }
            )
        }
        composable(
            route = Routes.SET_PIN
        ) {
            SetPinScreen(
                onBack = {
                    navController.goBack()
                },
                onPinSaved = {
                    navController.popBackStack()
                }
            )
        }
        composable(
            route = Routes.CHANGE_PIN
        ) {

            ChangePinScreen(

                onBack = {

                    navController.goBack()

                },

                onPinChanged = {

                    navController.popBackStack()

                }

            )

        }
        composable(
            route = Routes.VERIFY_PIN
        ) {

            VerifyPinScreen(

                title = "Disable PIN",

                showDisableDialog = true,

                onBack = {

                    navController.goBack()

                },

                onSuccess = {

                    navController.goBack()

                }

            )

        }
    }
}