package com.applications.toms.mykitchenhelper

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.compose.rememberNavController
import com.applications.toms.mykitchenhelper.ui.navigation.Navigation
import com.applications.toms.mykitchenhelper.ui.theme.MyKitchenHelperTheme

@ExperimentalMaterialApi
@ExperimentalComposeUiApi
@Composable
fun TimerApp() {

    TimerScreenTheme {
        val navController = rememberNavController()
        Navigation(navController)
    }

}

@Composable
fun TimerScreenTheme(content: @Composable () -> Unit) {

    MyKitchenHelperTheme() {

        Surface(color = MaterialTheme.colors.background) {
            content()
        }

    }

}