package com.who.climasense.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.who.climasense.R
import com.who.climasense.navigation.ClimaScreens
import com.who.climasense.utils.fontFamily

@Composable
fun NavigationPane(navController: NavController) {
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .width(170.dp)
                .padding(start = 16.dp, top = 16.dp)

        ) {
            CreateRow(image = R.drawable.add_city, text = "Search City"){
                navController.navigate(ClimaScreens.SearchScreen.name)
            }
            CreateRow(image = R.drawable.app_setting, text = "Settings"){
                navController.navigate(ClimaScreens.SettingsScreen.name)
            }
        }
}

@Composable
fun CreateRow(image: Int = R.drawable.add_city, text: String, onClick: () -> Unit) {

    var isVisible by remember { mutableStateOf(true) }

    AnimatedVisibility(
        visible = isVisible,
        enter = scaleIn(),
        exit = scaleOut(),
    ){
        Row(modifier = Modifier.padding(bottom = 10.dp)
            .clickable {
                isVisible = false
                onClick() },
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ){
            Image(
                painter = painterResource(id = image),
                contentDescription = null,
                modifier = Modifier.size(50.dp)
                    .padding(top = 8.dp, bottom = 8.dp)
            )
            Text(text = text,
                fontFamily = fontFamily,
                color = Color.White,
                fontSize = 20.sp
            )
        }
    }

}