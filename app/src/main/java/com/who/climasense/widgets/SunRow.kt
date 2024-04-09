package com.who.climasense.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.who.climasense.utils.epochToTime
import com.who.climasense.utils.fontFamily


@Composable
fun SunRow(sunrise: Int, sunset: Int) {
    val riseTime = epochToTime(sunrise.toLong())
    val setTime = epochToTime(sunset.toLong())
    Box(
        modifier = Modifier
            .padding(start = 40.dp)
            .clip(RoundedCornerShape(topEnd = 40.dp, bottomStart = 40.dp))
            .width(350.dp)
            .height(170.dp)
            .background(color = Color(0x4BFFFFFF))
            .border(
                width = 1.dp,
                color = Color.Yellow,
                shape = RoundedCornerShape(topEnd = 40.dp, bottomStart = 40.dp)
            ),
        contentAlignment = Alignment.Center
    ){
        Column(
            modifier = Modifier
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = "  Sunrise: $riseTime", color = Color.White,
                style = TextStyle(
                    fontSize = 30.sp,
                    fontFamily = fontFamily
                )
            )
            Spacer(modifier = Modifier.height(30.dp))
            Text(text = "Sunset: $setTime", color = Color.White,
                style = TextStyle(
                    fontSize = 30.sp,
                    fontFamily = fontFamily
                )
            )
        }
    }
}