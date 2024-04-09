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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.who.climasense.R
import com.who.climasense.data.DataOrException
import com.who.climasense.models.Weather
import com.who.climasense.screens.favorite.FavoriteViewModel
import com.who.climasense.screens.settings.SettingsViewModel
import com.who.climasense.widgets.CityDisplay
import com.who.climasense.widgets.CreateNavigationButton
import com.who.climasense.widgets.CreatePredictionRow
import com.who.climasense.widgets.CreateWRHRows
import com.who.climasense.widgets.IconAndTempDisplay
import com.who.climasense.widgets.SunRow

@Composable
fun ClimaMainScreen(navController: NavController, viewModel: MainViewModel, city: String?,
                    favViewModel: FavoriteViewModel,
                    settingsViewModel: SettingsViewModel = hiltViewModel()
) {
    val unit = settingsViewModel.unitList.collectAsState().value
    val unitType = if(unit.isEmpty()) "metric" else unit[0].unit
    Box(modifier = Modifier
        .fillMaxSize()
        .paint(
            painterResource(id = R.drawable.main_bg)
        )) {
        Column(modifier = Modifier.background(Color.Transparent)) {
            CreateNavigationButton(navController)
            Log.d("MainScreenCall", "Unit: $unitType")
            ShowData(viewModel = viewModel, city = city, favViewModel = favViewModel, unit = unitType)
        }

    }
}

@Composable
fun ShowData(viewModel: MainViewModel, city: String?, favViewModel: FavoriteViewModel, unit: String) {
    val savedWeatherData = viewModel.getLastSavedWeatherData()
    val weatherDataState: DataOrException<Weather, Boolean, Exception>
    //check internet connection
    if (!isNetworkAvailable(context = LocalContext.current)) {
        if(savedWeatherData == null){
            CircularProgressIndicator(modifier = Modifier.padding(start = 20.dp, top = 16.dp))
            Toast.makeText(LocalContext.current, "No Internet Connection and No data to Display", Toast.LENGTH_SHORT).show()
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
        value = viewModel.getWeatherData(city.toString(), unit)
        Log.d("MainScreenKaFunction", "Data: $unit")
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
//        Log.d("MainViewScreen", "Data: ${weatherData.exception}")
    }else if(weatherData.data != null){
        var currIdx by remember {
            mutableIntStateOf(0)
        }
        CityDisplay(weatherData.data!!.city.name,
            temp = weatherData.data!!.list[currIdx].main.temp.toString(),
            favViewModel = favViewModel)

        IconAndTempDisplay(
            weatherData.data!!.list[currIdx].weather[0].icon,
            weatherData.data!!.list[currIdx].main.temp.toString(),
            weatherData.data!!.list[currIdx].weather[0].description
        )

        CreateWRHRows(weatherData.data!!, currIdx)

        CreatePredictionRow(weatherData.data!!.list){
//            Log.d("MainScreen", "Selected Index: $it")
            currIdx = it
        }

        SunRow(
            weatherData.data!!.city.sunrise,
            weatherData.data!!.city.sunset
        )
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

