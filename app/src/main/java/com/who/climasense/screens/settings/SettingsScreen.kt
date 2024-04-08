package com.who.climasense.screens.settings

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.who.climasense.R
import com.who.climasense.models.Unit
import com.who.climasense.utils.fontFamily


@Composable
fun SettingScreen(
    navController: NavController,
    viewModel: SettingsViewModel = hiltViewModel()
){
    var isCelsius by remember { mutableStateOf(true) }
    var isFahrenheit by remember { mutableStateOf(false) }
    val choiceFromDb = viewModel.unitList.collectAsState().value
    val defaultChoice = if(choiceFromDb.isEmpty()) "metric" else choiceFromDb[0].unit
    if(defaultChoice == "metric"){
        isCelsius = true
        isFahrenheit = false
    }else{
        isCelsius = false
        isFahrenheit = true
    }
    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color(0xFF000000))
        .paint(painterResource(id = R.drawable.main_bg))
    ){
        Column(
            modifier = Modifier
                .fillMaxSize()
//                .background(Color.Transparent)
        ) {
            Row(modifier = Modifier.padding(bottom = 10.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(
                    onClick = {
                        navController.popBackStack()
                    },
                    modifier = Modifier
                        .padding(top = 10.dp),
                    colors = ButtonDefaults.buttonColors(Color.Transparent)
                ) {
                    Image(
                        painterResource(id = R.drawable.arrow_back), contentDescription = null,
                        Modifier.width(15.dp)
                    )
                }
                Text(modifier = Modifier
                    .padding(start = 110.dp, top = 10.dp),
                    text = "Settings",
                    style = TextStyle(
                        fontFamily = fontFamily,
                        color = Color.White,
                        fontSize = 20.sp
                    )
                )
            }

            Row(
                modifier = Modifier.padding(start = 100.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(modifier = Modifier
                    .padding(top = 12.dp, end = 10.dp),
                    text = "Celsius/Kmph      ",
                    style = TextStyle(
                        fontFamily = fontFamily,
                        color = Color.White,
                        fontSize = 20.sp
                    )
                )
                Switch(
                    checked = isCelsius,
                    onCheckedChange = { isChecked ->
                        if (isChecked) {
                            isFahrenheit = false
                            isCelsius = true
                            viewModel.deleteUnit()
                            viewModel.addUnit(Unit(unit = "metric"))
                        }
                    },
                    modifier = Modifier.padding(top = 16.dp),
                    colors = SwitchDefaults.colors(
                        checkedThumbColor = Color.Yellow,
                        checkedTrackColor = Color(0xFF797979)
                    )
                )
            }
            Row(
                modifier = Modifier.padding(start = 100.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(modifier = Modifier
                    .padding(top = 12.dp, end = 10.dp),
                    text = "Fahrenheit/Mph",
                    style = TextStyle(
                        fontFamily = fontFamily,
                        color = Color.White,
                        fontSize = 20.sp
                    )
                )
                Switch(
                    checked = isFahrenheit,
                    onCheckedChange = { isChecked ->
                        if (isChecked) {
                            isCelsius = false
                            isFahrenheit = true
                            viewModel.deleteUnit()
                            viewModel.addUnit(Unit(unit = "imperial"))
                        }
                    },
                    modifier = Modifier.padding(top = 16.dp),
                    colors = SwitchDefaults.colors(
                        checkedThumbColor = Color.Yellow,
                        checkedTrackColor = Color(0xFF797979)
                    )
                )
            }


        }
    }
}
