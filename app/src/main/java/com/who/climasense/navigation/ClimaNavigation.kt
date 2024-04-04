package com.who.climasense.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.who.climasense.screens.main.ClimaMainScreen
import com.who.climasense.screens.main.MainViewModel
import com.who.climasense.screens.search.SearchScreen
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
            val mainViewModel = hiltViewModel<MainViewModel>()
            ClimaMainScreen(navController = navController, viewModel = mainViewModel)
        }
        composable(ClimaScreens.SearchScreen.name){
            SearchScreen(navController = navController)
        }
    }
}