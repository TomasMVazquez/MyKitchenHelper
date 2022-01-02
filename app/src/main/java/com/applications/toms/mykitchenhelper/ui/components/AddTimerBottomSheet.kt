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
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.applications.toms.mykitchenhelper.MyTimer
import com.applications.toms.mykitchenhelper.R
import com.applications.toms.mykitchenhelper.ui.theme.Shapes
import com.applications.toms.mykitchenhelper.util.toTimeFormat

@ExperimentalComposeUiApi
@Composable
fun AddTimerBottomSheet(onTimerStart: (MyTimer) -> Unit){

    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current

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
        .padding(24.dp)
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
                            keyboardController?.hide()
                            focusManager.clearFocus()
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
                        timeSettled = "".toTimeFormat()
                        focusManager.clearFocus()
                    },
                    contentPadding = PaddingValues(
                        horizontal = 20.dp,
                        vertical = 12.dp
                    ),
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
            GenericSpacer(type = SpacerType.VERTICAL, padding = 2.dp)
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
            GenericSpacer(type = SpacerType.VERTICAL, padding = 4.dp)
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
            GenericSpacer(type = SpacerType.VERTICAL, padding = 4.dp)
        }
    }
}
