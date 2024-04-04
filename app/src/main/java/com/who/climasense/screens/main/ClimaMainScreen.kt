package com.who.climasense.screens.main

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.who.climasense.R
import com.who.climasense.data.DataOrException
import com.who.climasense.models.Weather
import com.who.climasense.widgets.CityDisplay
import com.who.climasense.widgets.CreateNavigationButton
import com.who.climasense.widgets.CreatePredictionRow
import com.who.climasense.widgets.CreateWRHRows
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
    val savedWeatherData = viewModel.getLastSavedWeatherData()
    val weatherDataState: DataOrException<Weather, Boolean, Exception>
    //check internet connection
    if (!isNetworkAvailable(context = LocalContext.current)) {
        if(savedWeatherData == null){
            CircularProgressIndicator(modifier = Modifier.padding(start = 20.dp, top = 16.dp))
            Toast.makeText(LocalContext.current, "No Internet Connection", Toast.LENGTH_SHORT).show()
            return
        }
        else{
            Toast.makeText(LocalContext.current, "No Internet Connection", Toast.LENGTH_SHORT).show()
            weatherDataState = DataOrException(data = savedWeatherData)
        }
    }
    else{
    weatherDataState = produceState(
        initialValue = DataOrException(isLoading = true)) {
        value = viewModel.getWeatherData("Nagpur", "metric")
        viewModel.saveWeatherData(value.data!!)
        }.value
    }

    // Combine the saved data with the latest data
    val weatherData = if (weatherDataState.isLoading == true && savedWeatherData != null) {
        DataOrException(data = savedWeatherData)
    } else {
        weatherDataState
    }

    if(weatherData.isLoading == true){
        CircularProgressIndicator(modifier = Modifier.padding(start = 20.dp, top = 16.dp))
        Log.d("MainViewScreen", "Data: ${weatherData.exception}")
    }else if(weatherData.data != null){
        CityDisplay(weatherData.data!!.city.name)

        IconAndTempDisplay(
            weatherData.data!!.list[0].weather[0].icon,
            weatherData.data!!.list[0].main.temp.toString(),
            weatherData.data!!.list[0].weather[0].description
        )

        CreateWRHRows(weatherData.data!!)

        CreatePredictionRow(weatherData.data!!.list)
    }
}

fun isNetworkAvailable(context: Context): Boolean {
    val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
    val network = connectivityManager?.activeNetwork ?: return false
    val networkCapabilities = connectivityManager.getNetworkCapabilities(network) ?: return false

    return networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)
            || networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)
            || networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)
}

