package com.applications.toms.mykitchenhelper.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import com.applications.toms.domain.MyTimer
import com.applications.toms.mykitchenhelper.ui.AppState
import com.applications.toms.mykitchenhelper.R
import com.applications.toms.mykitchenhelper.ui.theme.Shapes
import com.applications.toms.mykitchenhelper.util.toTimeFormat

@ExperimentalMaterialApi
@ExperimentalComposeUiApi
@Composable
fun AddTimerBottomSheet(appState: AppState,onTimerStart: (MyTimer) -> Unit){

    var input by rememberSaveable { mutableStateOf("") }
    val emptyName = stringResource(id = R.string.label_my_timer_name)

    val numbers = (1..9).map { it.toString() }.toMutableList().apply {
        add("00")
        add("0")
        add("")
    }.chunked(3)

    var timeSettled by rememberSaveable { mutableStateOf("00 : 00 : 00") }
    var timeClicked by rememberSaveable { mutableStateOf("") }

    LazyColumn(modifier = Modifier
        .fillMaxSize()
        .padding(dimensionResource(id = R.dimen.padding_bottom_sheet))
    ) {

        item {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                TextField(
                    value = input,
                    onValueChange = { newText ->
                        input = newText.trimStart { it == '0' || it == ' '}
                    },
                    label = { Text(text = emptyName) },
                    modifier = Modifier.weight(0.5f),
                    maxLines = 1,
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                    keyboardActions = KeyboardActions(
                        onDone = {
                            appState.hideKeyboard()
                            appState.clearFocus()
                        }
                    ),
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = Color.Transparent
                    )
                )

                Spacer(modifier = Modifier.weight(0.1f))
                
                Button(
                    modifier = Modifier.weight(0.2f),
                    onClick = {
                        onTimerStart(
                            MyTimer(
                                name = if (input.isEmpty()) emptyName else input,
                                time = timeClicked
                            )
                        )
                        input = ""
                        timeClicked = ""
                        timeSettled = timeClicked.toTimeFormat()
                        appState.clearFocus()
                    },
                    shape = Shapes.medium
                ) {
                    Text(
                        text = stringResource(id = R.string.start_btn),
                        maxLines = 1
                    )
                }
            }

        }

        item {
            GenericSpacer(
                type = SpacerType.VERTICAL,
                padding = dimensionResource(id = R.dimen.spacer_xsmall)
            )
        }

        item {
            Text(
                text = timeSettled,
                style = MaterialTheme.typography.h5,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                color = Color.Black
            )
        }

        item {
            GenericSpacer(
                type = SpacerType.VERTICAL,
                padding = dimensionResource(id = R.dimen.spacer_small)
            )
        }

        items(numbers){ rowList ->
            NumericKeyboard(rowList, onClick = { clicked ->
                if (clicked.isNotEmpty()){
                    clicked.forEach { numberClicked ->
                        if (timeClicked.length <= 5) timeClicked += numberClicked
                    }
                }else {
                    timeClicked = timeClicked.dropLast(1)
                }
                timeClicked = timeClicked.trimStart('0')
                timeSettled = timeClicked.toTimeFormat()
            })
        }

        item {
            GenericSpacer(
                type = SpacerType.VERTICAL,
                padding = dimensionResource(id = R.dimen.spacer_small)
            )
        }
    }
}
