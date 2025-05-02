package com.example.smartbudgettracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.smartbudgettracker.navigation.AppNavigation
import com.example.smartbudgettracker.ui.theme.SmartBudgetTrackerTheme
import com.example.smartbudgettracker.viewmodel.ExpenseViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SmartBudgetTrackerTheme {
                Surface(color = MaterialTheme.colors.background) {
                    val navController = rememberNavController()
                    val viewModel: ExpenseViewModel = viewModel()
                    AppNavigation(navController, viewModel)
                }
            }
        }
    }
}
