package com.applications.toms.mykitchenhelper.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.applications.toms.mykitchenhelper.util.toTimerStringFormat

/**
 * we can add here buttons for each timer to stop or cancel and delete from list
 */
@Composable
fun Timer(name: String,timeRemaining: Long) {

    Row(
        modifier = Modifier.fillMaxWidth().padding(vertical = 16.dp),
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
            style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Medium)
        )
    }

}
