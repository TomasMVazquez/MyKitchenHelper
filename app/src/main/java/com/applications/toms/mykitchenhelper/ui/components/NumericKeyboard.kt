package com.applications.toms.mykitchenhelper.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.ButtonDefaults.textButtonColors
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Backspace
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.applications.toms.mykitchenhelper.R

@Composable
fun NumericKeyboard(numbers: List<String>,onClick: (String) -> Unit){

    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = dimensionResource(id = R.dimen.no_padding),
                vertical = dimensionResource(id = R.dimen.padding_small)
            ),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        items(numbers) { number ->
            if(number.isNotEmpty()) {
                TextButton(
                    modifier = Modifier.size(dimensionResource(id = R.dimen.button_round)),
                    shape = CircleShape,
                    border = BorderStroke(dimensionResource(id = R.dimen.border_xxsmall), Color.LightGray),
                    colors = textButtonColors(
                        contentColor = Color.White,
                        backgroundColor = MaterialTheme.colors.primaryVariant
                    ),
                    onClick = { onClick(number) }
                ) {
                    Text(
                        text = number,
                        style = MaterialTheme.typography.body2
                    )
                }
            }else {
                OutlinedButton(
                    modifier = Modifier.size(dimensionResource(id = R.dimen.button_round)),
                    onClick = { onClick(number) },
                    border = BorderStroke(dimensionResource(id = R.dimen.border_xxsmall), MaterialTheme.colors.primary),
                    shape = CircleShape,
                    colors = ButtonDefaults.outlinedButtonColors(
                        backgroundColor = MaterialTheme.colors.background,
                        contentColor = MaterialTheme.colors.primary
                    )
                ) {
                    Icon(
                        imageVector = Icons.Filled.Backspace,
                        contentDescription = stringResource(
                            R.string.content_description_clear
                        )
                    )
                }
            }
        }
    }
}