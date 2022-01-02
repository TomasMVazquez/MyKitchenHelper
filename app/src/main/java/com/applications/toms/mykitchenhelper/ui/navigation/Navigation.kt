package com.applications.toms.mykitchenhelper.ui.navigation

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.applications.toms.mykitchenhelper.ui.screen.TimersScreen

@ExperimentalMaterialApi
@ExperimentalComposeUiApi
@Composable
fun Navigation(navController: NavHostController) {

    NavHost(navController = navController, startDestination = "home") {
        /**
         * TODO: To make it more expandable we should add NavItems with a Grapf builder...
         */
        composable("home") {
            TimersScreen()
        }
    }

}