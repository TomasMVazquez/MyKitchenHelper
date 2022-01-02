package com.applications.toms.mykitchenhelper.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp
import com.applications.toms.mykitchenhelper.util.toTimerStringFormat
import com.applications.toms.mykitchenhelper.R

/**
 * we can add here buttons for each timer to stop or cancel and delete from list
 */
@Composable
fun Timer(name: String,timeRemaining: Long) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = if (timeRemaining < 6 && timeRemaining.toInt() % 2 != 0) Color.Yellow else Color.White
            )
            .padding(dimensionResource(id = R.dimen.padding_large)),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        Text(
            modifier = Modifier.weight(1f, fill= false),
            text = name,
            style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Medium),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )

        Text(
            modifier = Modifier,
            text = timeRemaining.toTimerStringFormat(),
            maxLines = 1,
            style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Medium),
            color = if (timeRemaining < 11) Color.Red else Color.Black
        )
    }

}
