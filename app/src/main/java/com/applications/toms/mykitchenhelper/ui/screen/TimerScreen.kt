package com.applications.toms.mykitchenhelper.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.applications.toms.mykitchenhelper.R
import com.applications.toms.mykitchenhelper.ui.components.Timer

/**
 * TODO: We should add all DPs to a dimension res and not adding directly on components
 * TODO : FIX ERROR WHEN IS COUNTING AND THE APP GOES TO BACKGROUND AND RETURNS TO FOREGROUND (IndexOutOfBoundsException: Index: 0, Size: 0)
 */

@ExperimentalMaterialApi
@ExperimentalComposeUiApi
@Composable
fun TimersScreen(state: List<Long>, timersName: MutableList<String>) {

    Column(modifier = Modifier.padding(vertical = dimensionResource(id = R.dimen.padding_large))) {

        Text(
            modifier = Modifier.padding(
                start = dimensionResource(id = R.dimen.padding_large),
                top = dimensionResource(id = R.dimen.no_padding),
                end = dimensionResource(id = R.dimen.padding_large),
                bottom = dimensionResource(id = R.dimen.padding_small)
            ),
            text = stringResource(R.string.running_label),
            style = MaterialTheme.typography.h1,
        )

        Divider(
            thickness = dimensionResource(id = R.dimen.spacer_xsmall),
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

                Divider(modifier = Modifier.padding(horizontal = dimensionResource(id = R.dimen.padding_large)))
            }
        }
    }

}
