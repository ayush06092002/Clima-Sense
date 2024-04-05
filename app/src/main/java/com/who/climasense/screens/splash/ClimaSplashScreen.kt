package com.who.climasense.screens.splash

import android.media.Image
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.who.climasense.R
import com.who.climasense.navigation.ClimaScreens
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun ClimaSplashScreen(navController: NavController) {
    val coroutineScope = rememberCoroutineScope()
    val defaultCity = "Kanpur"
    LaunchedEffect(key1 = true) {
        coroutineScope.launch {
            delay(1500)
            navController.navigate(ClimaScreens.MainScreen.name + "/$defaultCity")
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