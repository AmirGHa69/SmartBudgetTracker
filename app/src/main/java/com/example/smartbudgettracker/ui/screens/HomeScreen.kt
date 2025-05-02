package com.example.smartbudgettracker.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.DismissDirection
import androidx.compose.material.DismissValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.rememberDismissState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.smartbudgettracker.data.model.Expense
import com.example.smartbudgettracker.navigation.NavRoutes
import com.example.smartbudgettracker.viewmodel.ExpenseViewModel

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: ExpenseViewModel = viewModel()
) {
    val expenses by viewModel.expenses.collectAsState()
    val totalSpent by viewModel.totalSpent.collectAsState()
    val budget by viewModel.budget.collectAsState()
    var searchQuery by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Smart Budget Tracker") },
                actions = {
                    TextButton(onClick = { navController.navigate(NavRoutes.CATEGORY_CHART) }) {
                        Text("Chart", color = MaterialTheme.colors.onPrimary)
                    }
                    TextButton(onClick = { navController.navigate(NavRoutes.SETTINGS) }) {
                        Text("Settings", color = MaterialTheme.colors.onPrimary)
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                navController.navigate(NavRoutes.ADD_EXPENSE)
            }) { Text("+") }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ) {
            Text("Monthly Budget: $${budget}")
            Text("Total Spent: $${totalSpent}")
            Text(
                text = "Remaining: $${budget - totalSpent}",
                color = if (totalSpent > budget) MaterialTheme.colors.error
                else MaterialTheme.colors.primary
            )
            Spacer(Modifier.height(16.dp))

            OutlinedTextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                label = { Text("Search by description or category") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(Modifier.height(8.dp))

            val filtered = expenses.filter {
                it.description.contains(searchQuery, ignoreCase = true) ||
                        it.category.contains(searchQuery, ignoreCase = true)
            }

            if (filtered.isEmpty()) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text("No matching expenses.")
                }
            } else {
                LazyColumn {
                    items(filtered, key = { it.id }) { expense ->
                        val dismissState = rememberDismissState(
                            confirmStateChange = {
                                if (it == DismissValue.DismissedToEnd || it == DismissValue.DismissedToStart) {
                                    viewModel.deleteExpense(expense)
                                    true
                                } else false
                            }
                        )

                        SwipeToDismiss(
                            state = dismissState,
                            directions = setOf(
                                DismissDirection.StartToEnd,
                                DismissDirection.EndToStart
                            ),
                            background = {},
                            dismissContent = {
                                ExpenseItem(expense)
                            }
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun ExpenseItem(expense: Expense) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        elevation = 4.dp
    ) {
        Column(Modifier.padding(16.dp)) {
            Text("${expense.description} - $${expense.amount} (${expense.category})")
        }
    }
}
