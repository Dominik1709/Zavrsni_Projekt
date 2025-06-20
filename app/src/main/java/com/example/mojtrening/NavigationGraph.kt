package com.example.mojtrening

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.mojtrening.ui.theme.screen.LoginScreen
import com.example.mojtrening.ui.theme.screen.SettingsScreen
import com.example.mojtrening.ui.theme.screen.TreningScreen

@Composable
fun NavigationGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "login_screen") {
        composable("login_screen") {
            LoginScreen(navController)
        }
        composable("settings_screen") {
            SettingsScreen(navController)
        }
        composable("trening_screen") {
            TreningScreen(navController)
        }
    }
}
