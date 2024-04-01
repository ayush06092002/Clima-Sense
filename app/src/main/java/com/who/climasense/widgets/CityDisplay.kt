package com.who.climasense.widgets

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.who.climasense.utils.fontFamily
import java.text.SimpleDateFormat
import java.util.Date

@SuppressLint("SimpleDateFormat")
@Composable
fun CityDisplay(city: String = "Kanpur") {
    Column(modifier = Modifier
        .padding(start = 16.dp, top = 14.dp)) {
        Text(
            text = "Today",
            style = TextStyle(
                fontFamily = fontFamily,
                fontSize = 16.sp,
                color = Color.White
            )
        )
        Text(
            text = city,
            style = TextStyle(
                fontFamily = fontFamily,
                fontSize = 32.sp,
                color = Color.White,
            )
        )
        Text(
            text = SimpleDateFormat("dd MMMM yyyy").format(Date()),
            style = TextStyle(
                fontFamily = fontFamily,
                fontSize = 12.sp,
                color = Color.White,
            )
        )
    }
}