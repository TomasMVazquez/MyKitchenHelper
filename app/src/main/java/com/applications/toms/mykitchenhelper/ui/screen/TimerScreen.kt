package com.applications.toms.mykitchenhelper.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreTime
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.applications.toms.mykitchenhelper.AppState
import com.applications.toms.mykitchenhelper.R
import com.applications.toms.mykitchenhelper.ui.components.AddTimerBottomSheet
import com.applications.toms.mykitchenhelper.ui.components.Timer
import com.applications.toms.mykitchenhelper.ui.theme.BottomSheetShape
import com.applications.toms.mykitchenhelper.util.toTimer
import kotlinx.coroutines.launch

/**
 * TODO: We should add all DPs to a dimension res and not adding directly on components
 * TODO : FIX ERROR WHEN IS COUNTING AND THE APP GOES TO BACKGROUND AND RETURNS TO FOREGROUND (IndexOutOfBoundsException: Index: 0, Size: 0)
 */

@ExperimentalMaterialApi
@ExperimentalComposeUiApi
@Composable
fun TimersScreen(
    appState: AppState,
    viewModel: TimerViewModel = TimerViewModel()
) {

    val scope = appState.coroutineScope

    val state by viewModel.state.collectAsState()

    val timersName = mutableListOf<String>()

    ModalBottomSheetLayout(
        sheetContent = {
            AddTimerBottomSheet(appState = appState){
                scope.launch {
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
                FloatingActionButton(
                    onClick = {
                        scope.launch {
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
        ) { paddingValues ->

            Box(modifier = Modifier.padding(paddingValues = paddingValues)) {

                Column(modifier = Modifier.padding(vertical = 16.dp)) {

                    Text(
                        modifier = Modifier.padding(
                            start = 16.dp,
                            top = 0.dp,
                            end = 16.dp,
                            bottom = 8.dp
                        ),
                        text = stringResource(R.string.running_label),
                        style = MaterialTheme.typography.h1,
                    )

                    Divider(
                        thickness = 2.dp,
                        color = Color.Black
                    )

                    LazyColumn(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                    ) {
                        itemsIndexed(state) { index, timeRemaining ->

                            Timer(
                                name = timersName[index],
                                timeRemaining = timeRemaining
                            )

                            Divider(modifier = Modifier.padding(horizontal = 16.dp))
                        }
                    }

                }
            }

        }
    }

}
