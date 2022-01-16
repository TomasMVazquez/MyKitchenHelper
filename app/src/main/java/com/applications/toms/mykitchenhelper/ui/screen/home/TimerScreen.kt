package com.applications.toms.mykitchenhelper.ui.screen.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.applications.toms.mykitchenhelper.R
import com.applications.toms.mykitchenhelper.ui.components.Timer

@ExperimentalMaterialApi
@ExperimentalComposeUiApi
@Composable
fun TimersScreen(state: List<Long>, timersName: MutableList<String>, infoClick: () -> Unit) {

    Column(modifier = Modifier.padding(vertical = dimensionResource(id = R.dimen.padding_large))) {
        Row(
            modifier = Modifier.fillMaxWidth()
                .padding(
                    start = dimensionResource(id = R.dimen.padding_large),
                    top = dimensionResource(id = R.dimen.no_padding),
                    end = dimensionResource(id = R.dimen.no_padding),
                    bottom = dimensionResource(id = R.dimen.padding_small)
                ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = stringResource(R.string.running_label),
                style = MaterialTheme.typography.h1,
            )

            IconButton(onClick = infoClick) {
                Icon(
                    imageVector = Icons.Outlined.Info,
                    contentDescription = stringResource(id = R.string.about_us)
                )
            }
        }

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
