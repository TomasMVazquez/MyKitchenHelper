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
 */

@ExperimentalMaterialApi
@ExperimentalComposeUiApi
@Composable
fun TimersScreen(
    appState: AppState,
    viewModel: TimerViewModel
) {

    val scope = appState.coroutineScope

    val state by viewModel.state.collectAsState()

    val timersName = mutableListOf<String>()

    ModalBottomSheetLayout(
        sheetContent = {
            AddTimerBottomSheet(){
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

                Column(modifier = Modifier.padding(16.dp)) {

                    Text(
                        text = stringResource(R.string.running_label),
                        style = MaterialTheme.typography.h1,
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Divider()

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
                            Divider()
                        }
                    }

                }
            }

        }
    }

}
