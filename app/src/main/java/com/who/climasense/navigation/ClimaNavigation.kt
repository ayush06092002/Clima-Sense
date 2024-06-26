package com.who.climasense.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.who.climasense.screens.favorite.FavoriteViewModel
import com.who.climasense.screens.main.ClimaMainScreen
import com.who.climasense.screens.main.MainViewModel
import com.who.climasense.screens.search.SearchScreen
import com.who.climasense.screens.settings.SettingScreen
import com.who.climasense.screens.settings.SettingsViewModel
import com.who.climasense.screens.splash.ClimaSplashScreen

@Composable
fun ClimaNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController,
        startDestination = ClimaScreens.SplashScreen.name) {
        composable(ClimaScreens.SplashScreen.name) {
                ClimaSplashScreen(navController = navController)
            }


        val route = ClimaScreens.MainScreen.name

        composable("$route/{city}",
            arguments = listOf(
                navArgument("city") {
                    type = NavType.StringType
                }
            )
        ) { backStackEntry ->
            backStackEntry.arguments?.getString("city").let { city ->
                val mainViewModel = hiltViewModel<MainViewModel>()
                val favoriteViewModel = hiltViewModel<FavoriteViewModel>()
                ClimaMainScreen(navController = navController,
                    viewModel = mainViewModel,
                    city = city,
                    favViewModel = favoriteViewModel)
            }
        }
        composable(ClimaScreens.SearchScreen.name){
            SearchScreen(navController)
        }

        composable(ClimaScreens.SettingsScreen.name){
            SettingScreen(navController)
        }
    }
}