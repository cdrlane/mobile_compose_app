package com.example.mobile_compose_app.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun ListHeader(instrumentType:String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .padding(start = 25.dp, end = 35.dp, top = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = instrumentType, color = Color.Gray, fontWeight = FontWeight.Bold)

        Icon(
            imageVector = Icons.Filled.Info,
            contentDescription = "Instrument Icon",
            tint = Color.Black,
            modifier = Modifier
                .size(25.dp)
                .padding(bottom = 3.dp)
                .drawBehind {
                    drawCircle(color = Color.Black, radius = size.height / 2f + 4f)
                    drawCircle(color = Color.White, radius = size.height / 2f + 3f)
                }
        )
    }
}