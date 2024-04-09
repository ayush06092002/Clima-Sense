package com.who.climasense.widgets

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.who.climasense.utils.fontFamily

@Composable
fun IconAndTempDisplay(imageUrl: String, temp: String
, description: String){
    val imgUrl = "https://openweathermap.org/img/wn/$imageUrl@2x.png"
//    Log.d("IconAndTempDisplay", "Icon URL: $imgUrl")
    val integerPart = temp.substringBefore(".")
    Surface(modifier = Modifier
        .fillMaxWidth()
        .height(150.dp)
        ,
        color = Color.Transparent){
        Row(modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically){
            Image(
                modifier = Modifier
                    .width(200.dp)
                    .height(180.dp)
                    .padding(16.dp),
                painter = rememberAsyncImagePainter(model = imgUrl),
                contentDescription = "Weather Icon"
            )
            Spacer(modifier = Modifier.width(55.dp))
            Column(modifier = Modifier,
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Row(modifier = Modifier
                ) {
                    Text(
                        text = integerPart,
                        style = TextStyle(
                            fontFamily = fontFamily,
                            fontSize = 64.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    )
                    Text(modifier = Modifier.padding(top = 10.dp),
                        text = "°",
                        color = Color.Yellow,
                        style = TextStyle(
                            fontFamily = fontFamily,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 35.sp,
                            color = Color.Yellow
                        )
                    )
                }
                Text(modifier = Modifier.padding(end = 16.dp),
                    text = description,
                    style = TextStyle(
                        fontFamily = fontFamily,
                        fontSize = 20.sp,
                        color = Color.White
                    )
                )
            }

        }
    }
}