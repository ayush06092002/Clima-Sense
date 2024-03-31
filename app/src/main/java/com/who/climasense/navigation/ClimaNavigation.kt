package com.who.climasense.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.who.climasense.screens.main.ClimaMainScreen
import com.who.climasense.screens.splash.ClimaSplashScreen

@Composable
fun ClimaNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController,
        startDestination = ClimaScreens.SplashScreen.name) {
        composable(ClimaScreens.SplashScreen.name) {
            ClimaSplashScreen(navController = navController)
        }
        composable(ClimaScreens.MainScreen.name) {
            ClimaMainScreen(navController = navController)
        }
    }
}