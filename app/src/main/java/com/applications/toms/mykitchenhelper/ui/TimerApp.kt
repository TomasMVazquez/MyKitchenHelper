package com.applications.toms.mykitchenhelper.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreTime
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.applications.toms.mykitchenhelper.R
import com.applications.toms.mykitchenhelper.ui.components.AddTimerBottomSheet
import com.applications.toms.mykitchenhelper.ui.navigation.Navigation
import com.applications.toms.mykitchenhelper.ui.screen.home.TimerViewModel
import com.applications.toms.mykitchenhelper.ui.theme.BottomSheetShape
import com.applications.toms.mykitchenhelper.ui.theme.MyKitchenHelperTheme
import com.applications.toms.mykitchenhelper.util.toTimer
import kotlinx.coroutines.launch

@ExperimentalMaterialApi
@ExperimentalComposeUiApi
@Composable
fun TimerApp(
    appState: AppState = rememberAppState(),
    viewModel: TimerViewModel = TimerViewModel()
) {

    TimerScreenTheme {

        val state by viewModel.state.collectAsState()

        val timersName = rememberSaveable { mutableListOf<String>() }

        ModalBottomSheetLayout(
            sheetContent = {
                AddTimerBottomSheet(appState = appState){
                    appState.coroutineScope.launch {
                        timersName.add(it.name)
                        viewModel.start(it.time.toTimer())
                        appState.hideModalSheet()
                    }
                }
            },
            sheetState = appState.modalBottomSheetState,
            sheetShape = BottomSheetShape
        ) {
            Scaffold(
                scaffoldState = appState.scaffoldState,
                floatingActionButton = {
                    if (appState.showFloatingActionButton) {
                        FloatingActionButton(
                            onClick = {
                                appState.coroutineScope.launch {
                                    appState.showModalSheet()
                                }
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Filled.MoreTime,
                                contentDescription = stringResource(R.string.content_desc_add_timer)
                            )
                        }
                    }
                }
            ) { paddingValues ->

                Box(modifier = Modifier.padding(paddingValues)) {
                    Navigation(
                        appState = appState,
                        state = state,
                        timersName = timersName
                    )
                }

            }
        }
        
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