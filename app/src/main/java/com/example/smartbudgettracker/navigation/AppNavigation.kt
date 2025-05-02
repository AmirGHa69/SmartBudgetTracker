package com.example.smartbudgettracker.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.smartbudgettracker.ui.screens.*
import com.example.smartbudgettracker.viewmodel.ExpenseViewModel

@Composable
fun AppNavigation(navController: NavHostController, viewModel: ExpenseViewModel) {
    NavHost(
        navController = navController,
        startDestination = NavRoutes.HOME
    ) {
        composable(NavRoutes.HOME) {
            HomeScreen(navController, viewModel)
        }
        composable(NavRoutes.ADD_EXPENSE) {
            AddExpenseScreen(navController, viewModel) // ✅ fixed
        }
        composable(NavRoutes.CATEGORY_CHART) {
            CategoryChartScreen(navController, viewModel) // ✅ fixed
        }
        composable(NavRoutes.SETTINGS) {
            SettingsScreen(navController, viewModel) // ✅ fixed
        }
    }
}
