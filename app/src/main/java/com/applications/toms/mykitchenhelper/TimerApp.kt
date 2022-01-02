package com.applications.toms.mykitchenhelper

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import com.applications.toms.mykitchenhelper.ui.navigation.Navigation
import com.applications.toms.mykitchenhelper.ui.theme.MyKitchenHelperTheme

@ExperimentalMaterialApi
@ExperimentalComposeUiApi
@Composable
fun TimerApp(appState: AppState = rememberAppState()) {

    TimerScreenTheme {
        Navigation(appState = appState)
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