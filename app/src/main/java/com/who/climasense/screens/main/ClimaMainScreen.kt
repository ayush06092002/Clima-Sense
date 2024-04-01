package com.who.climasense.screens.main

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import com.who.climasense.R
import com.who.climasense.data.DataOrException
import com.who.climasense.models.City
import com.who.climasense.models.Weather
import com.who.climasense.widgets.CityDisplay
import com.who.climasense.widgets.CreateNavigationButton

@Composable
fun ClimaMainScreen(navController: NavController, viewModel: MainViewModel) {
    Box(modifier = Modifier
        .fillMaxSize()
        .paint(
            painterResource(id = R.drawable.main_bg)
        )) {
        Column {
            CreateNavigationButton()
            ShowData(viewModel = viewModel)
        }

    }
}

@Composable
fun ShowData(viewModel: MainViewModel) {
    val weatherData = produceState<DataOrException<Weather, Boolean, Exception>>(
        initialValue = DataOrException(isLoading = true)) {
        value = viewModel.getWeatherData("Kanpur", "metric")
    }.value

    if(weatherData.isLoading == true){
        CircularProgressIndicator()
        Log.d("MainViewScreen", "Data: ${weatherData.exception}")
    }else if(weatherData.data != null){
        CityDisplay(weatherData.data!!.city.name)
//        Text(text = weatherData.data!!.city.country)
    }
}

