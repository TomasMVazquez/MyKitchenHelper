package com.applications.toms.mykitchenhelper.ui.navigation

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.applications.toms.mykitchenhelper.AppState
import com.applications.toms.mykitchenhelper.ui.screen.TimersScreen

@ExperimentalMaterialApi
@ExperimentalComposeUiApi
@Composable
fun Navigation(appState: AppState) {

    NavHost(navController = appState.navController, startDestination = "home") {
        /**
         * TODO: To make it scalable we should add NavItems with a Grapf builder...
         */
        composable("home") {
            TimersScreen(
                appState = appState
            )
        }
    }

}