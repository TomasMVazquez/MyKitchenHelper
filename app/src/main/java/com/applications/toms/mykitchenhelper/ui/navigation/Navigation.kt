package com.applications.toms.mykitchenhelper.ui.navigation

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.applications.toms.mykitchenhelper.ui.AppState
import com.applications.toms.mykitchenhelper.ui.screen.home.TimersScreen
import com.applications.toms.mykitchenhelper.ui.screen.aboutus.AboutUsScreen
import kotlinx.coroutines.launch

@ExperimentalMaterialApi
@ExperimentalComposeUiApi
@Composable
fun Navigation(appState: AppState, state: List<Long>, timersName: MutableList<String>) {

    NavHost(
        navController = appState.navController,
        startDestination = NavCommand.ContentType(NavFeature.HOME).route
    ) {

        composable(NavCommand.ContentType(NavFeature.HOME).route) {
            TimersScreen(
                state = state,
                timersName = timersName
            ) {
                appState.navController.navigate(NavCommand.ContentType(NavFeature.ABOUT_US).route)
            }
        }

        composable(NavCommand.ContentType(NavFeature.ABOUT_US).route) {
            appState.coroutineScope.launch {
                appState.modalBottomSheetState.hide()
            }
            AboutUsScreen() {
                appState.navController.popBackStack()
            }
        }

    }

}

private fun NavGraphBuilder.composable(
    navCommand: NavCommand,
    content: @Composable (NavBackStackEntry) -> Unit
) {
    composable(
        route = navCommand.route,
        arguments = navCommand.args
    ){
        content(it)
    }
}