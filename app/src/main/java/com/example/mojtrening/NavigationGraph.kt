package com.example.mojtrening

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.mojtrening.ui.theme.screen.DashboardScreen
import com.example.mojtrening.ui.theme.screen.LoginScreen
import com.example.mojtrening.ui.theme.screen.TreningScreen
import com.example.mojtrening.viewmodel.TreningViewModel

@Composable
fun NavigationGraph(navController: NavHostController) {
    val treningViewModel: TreningViewModel = viewModel()

    NavHost(navController = navController, startDestination = "login_screen") {

        composable("login_screen") {
            LoginScreen(navController)
        }


        composable("trening_screen") {
            TreningScreen(navController, treningViewModel)
        }

        composable("dashboard_screen") {
            DashboardScreen(navController, treningViewModel)
        }

    }
}
