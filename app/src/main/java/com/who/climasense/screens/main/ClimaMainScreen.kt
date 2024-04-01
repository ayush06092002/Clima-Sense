package com.who.climasense.screens.main

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.who.climasense.R
import com.who.climasense.data.DataOrException
import com.who.climasense.models.City
import com.who.climasense.models.Weather
import com.who.climasense.widgets.CityDisplay
import com.who.climasense.widgets.CreateNavigationButton
import com.who.climasense.widgets.IconAndTempDisplay

@Composable
fun ClimaMainScreen(navController: NavController, viewModel: MainViewModel) {
    Box(modifier = Modifier
        .fillMaxSize()
        .paint(
            painterResource(id = R.drawable.main_bg)
        )) {
        Column(modifier = Modifier.background(Color.Transparent)) {
            CreateNavigationButton()
            ShowData(viewModel = viewModel)

        }

    }
}

@Composable
fun ShowData(viewModel: MainViewModel) {
    val weatherData = produceState<DataOrException<Weather, Boolean, Exception>>(
        initialValue = DataOrException(isLoading = true)) {
        value = viewModel.getWeatherData("Nagpur", "metric")
    }.value

    if(weatherData.isLoading == true){
        CircularProgressIndicator(modifier = Modifier.padding(start = 20.dp, top = 16.dp))
        Log.d("MainViewScreen", "Data: ${weatherData.exception}")
    }else if(weatherData.data != null){
        CityDisplay(weatherData.data!!.city.name)

        IconAndTempDisplay(
            weatherData.data!!.list[0].weather[0].icon,
            weatherData.data!!.list[0].main.temp.toString(),
            weatherData.data!!.list[0].weather[0].description)
    }
}

