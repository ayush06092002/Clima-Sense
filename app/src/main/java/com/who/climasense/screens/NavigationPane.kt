package com.who.climasense.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.who.climasense.R
import com.who.climasense.utils.fontFamily


@Composable
fun NavigationPane() {
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .width(150.dp)
                .padding(top = 16.dp)

        ) {
            CreateRow(image = R.drawable.add_city, text = "Add City")
            CreateRow(image = R.drawable.add_city, text = "Settings")
            CreateRow(image = R.drawable.map, text = "Map")
        }
}

@Composable
fun CreateRow(image: Int = R.drawable.add_city, text: String = "Add City") {
    Row(horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically) {
        Image(
            painter = painterResource(id = image),
            contentDescription = null,
            modifier = Modifier
                .width(40.dp)
                .padding(start = 8.dp, top = 8.dp, bottom = 8.dp)
        )
        Text(text = text,
            fontFamily = fontFamily,
            color = Color.White
        )
    }
}