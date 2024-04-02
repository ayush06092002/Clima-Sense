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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.who.climasense.R
import com.who.climasense.utils.fontFamily

//@Preview
@Composable
fun CreateWRHRows() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly,
    ) {
        AddIconBox(R.drawable.wind, "Wind")
        AddIconBox(R.drawable.rain, "Rain")
        AddIconBox(R.drawable.humid, "Humidity")
    }
}

@Composable
fun AddIconBox(icon: Int = R.drawable.rain, s: String = "32%") {
    Column(
        modifier = Modifier.padding(end = 0.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Surface(
            modifier = Modifier
                .width(120.dp)
                .height(120.dp)
//                .clip(RoundedCornerShape(100.dp))
                .padding(20.dp),
            shape = RoundedCornerShape(10.dp),
            color = Color(0x81FFFFFF)
        ) {
            Image(modifier = Modifier
                .size(50.dp)
                .padding(20.dp),
                painter = painterResource(id = icon), contentDescription = s)
        }
        Text(modifier = Modifier,
            text = s,
            style = TextStyle(
                fontFamily = fontFamily,
                fontSize = 24.sp
            ),
            color = Color.White
        )
    }
}

