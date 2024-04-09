package com.who.climasense.screens.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.who.climasense.R
import com.who.climasense.navigation.ClimaScreens
import com.who.climasense.screens.settings.SettingsViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun ClimaSplashScreen(navController: NavController) {
    val coroutineScope = rememberCoroutineScope()
    val settingsViewModel = hiltViewModel<SettingsViewModel>()
    val unitList = settingsViewModel.unitList.collectAsState().value
    var defaultCity by remember {
        mutableStateOf("")
    }
    if(unitList.isNotEmpty()){
        defaultCity = unitList[0].defaultCity
//        Log.d("ClimaSplashScreen", "Default City: $defaultCity")
    }
    LaunchedEffect(key1 = true) {
        coroutineScope.launch {
            delay(1500)
            if(defaultCity.isBlank()){
//                Log.d("ClimaSplashScreen", "Navigating to Settings Screen $defaultCity")
                navController.navigate(ClimaScreens.SettingsScreen.name)
            }else{
//                Log.d("ClimaSplashScreen", "Navigating to Main Screen")
                navController.navigate(ClimaScreens.MainScreen.name + "/$defaultCity")
            }
        }
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF51C4FB)),
        contentAlignment = Alignment.Center
    ) {
        Image(painterResource(id = R.mipmap.ic_launcher_logo_2_foreground),
            contentDescription = "Clima Logo",
            modifier = Modifier.size(200.dp))
    }
}