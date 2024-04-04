package com.who.climasense.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.who.climasense.models.WeatherItem
import com.who.climasense.utils.epochToDate
import com.who.climasense.utils.epochToTime
import com.who.climasense.utils.fontFamily


@Composable
fun CreatePredictionRow(list: List<WeatherItem>, onClick: (Int) -> Unit) {
    var selectedIdx by remember {
        mutableIntStateOf(0)
    }
    Column(
        modifier = Modifier
            .padding(8.dp)
            .background(Color.Transparent),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
            LazyRow(
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth()
                    .height(200.dp)
            ) {
                items(list.size) {curr ->
                    val date = epochToDate(list[curr].dt)
                    val time = epochToTime(list[curr].dt.toLong())
                    val icon = list[curr].weather[0].icon
                    val temp = list[curr].main.temp.toString().substringBefore(".")
                    val units = "metric"

                    CreatePredictionSurface(date, time, icon, temp, units,
                        curr == selectedIdx) {
                        selectedIdx = curr
                        onClick(curr)
                    }
                }

            }
    }
}



@Composable
fun CreatePredictionSurface(date: String, time: String, icon: String, temp: String, units: String,
                            isActive: Boolean, onSelected: () -> Unit) {
    Surface(
        modifier = Modifier
            .width(120.dp)
            .height(180.dp)
            .padding(15.dp)
            .clip(RoundedCornerShape(topEnd = 25.dp, bottomStart = 25.dp))
            .border(if(isActive) 2.dp else 1.dp, Color.Yellow, RoundedCornerShape(topEnd = 25.dp, bottomStart = 25.dp)),
        color = Color.Transparent.copy(alpha = if (isActive) 1f else 0.3f),
        onClick = onSelected
    ) {
        Column(
            modifier = Modifier.padding(8.dp),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = date,
                color = Color.White,
                fontFamily = fontFamily,
                fontWeight = FontWeight.SemiBold
            )
            Text(
                text = time,
                color = Color.White,
                fontFamily = fontFamily,
                fontWeight = FontWeight.SemiBold,
                fontSize = 12.sp
            )
            Image(modifier = Modifier
                .width(50.dp)
                .height(50.dp),
                painter = rememberAsyncImagePainter(model = "https://openweathermap.org/img/wn/$icon.png"),
                contentDescription = "Weather Icon")
            if(units == "metric"){
                Text(
                    text = "$temp°C",
                    color = Color.White,
                    fontFamily = fontFamily,
                    fontWeight = FontWeight.SemiBold
                )
            }else{
                Text(
                    text = "$temp°F",
                    color = Color.White,
                    fontFamily = fontFamily,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }
}