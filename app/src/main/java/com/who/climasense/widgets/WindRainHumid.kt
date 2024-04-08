package com.who.climasense.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.who.climasense.R
import com.who.climasense.models.Weather
import com.who.climasense.utils.fontFamily

@Composable
fun CreateWRHRows(data: Weather, currIdx: Int) {
    val intValueRain = (data.list[currIdx].pop * 100).toString().substringBefore(".")
    val windSpeed = data.list[currIdx].wind.speed.toString()
    val humidity = data.list[currIdx].main.humidity.toString()
    val deg = data.list[currIdx].wind.deg
    val direction = when(deg){
        in 0..22 -> "N"
        in 23..67 -> "NE"
        in 68..112 -> "E"
        in 113..157 -> "SE"
        in 158..202 -> "S"
        in 203..247 -> "SW"
        in 248..292 -> "W"
        in 293..337 -> "NW"
        in 338..360 -> "N"
        else -> "N"
    }
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly,
    ) {
        AddIconBox(R.drawable.wind, "$windSpeed ($direction)", "metric")
        AddIconBox(R.drawable.rain, intValueRain, "metric")
        AddIconBox(R.drawable.humid, humidity, "metric")
    }
}

@Composable
fun AddIconBox(icon: Int, info: String, units: String) {
    Column(
        modifier = Modifier.padding(end = 0.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Surface(
            modifier = Modifier
                .width(120.dp)
                .height(120.dp)
//                .clip(RoundedCornerShape(100.dp))
                .padding(top = 20.dp, bottom = 10.dp, start = 20.dp, end = 20.dp),
            shape = RoundedCornerShape(10.dp),
            color = Color(0x81FFFFFF)
        ) {
            Image(
                modifier = Modifier
                    .size(50.dp)
                    .padding(20.dp),
                painter = painterResource(id = icon), contentDescription = "Icon"
            )
        }
        if (icon == R.drawable.wind) {
            if (units == "metric") {
                Text(
                    modifier = Modifier,
                    text = "$info kmph",
                    style = TextStyle(
                        fontFamily = fontFamily,
                        fontSize = 16.sp
                    ),
                    color = Color.White
                )
            } else {
                Text(
                    modifier = Modifier,
                    text = "info mph",
                    style = TextStyle(
                        fontFamily = fontFamily,
                        fontSize = 16.sp
                    ),
                    color = Color.White
                )
            }
        }
        else if(icon == R.drawable.rain){
            Text(
                modifier = Modifier,
                text = "$info%",
                style = TextStyle(
                    fontFamily = fontFamily,
                    fontSize = 16.sp
                ),
                color = Color.White
            )
        }
        else if(icon == R.drawable.humid){
            Text(
                modifier = Modifier,
                text = "$info%",
                style = TextStyle(
                    fontFamily = fontFamily,
                    fontSize = 16.sp
                ),
                color = Color.White
            )
        }
    }
}

