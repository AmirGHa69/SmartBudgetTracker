package com.example.smartbudgettracker.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.smartbudgettracker.viewmodel.ExpenseViewModel
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.formatter.PercentFormatter
import com.github.mikephil.charting.utils.ColorTemplate

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun CategoryChartScreen(
    navController: NavController,
    viewModel: ExpenseViewModel = viewModel()
) {
    val expenses by viewModel.expenses.collectAsState()
    val budget by viewModel.budget.collectAsState()
    val totalSpent by viewModel.totalSpent.collectAsState()

    val grouped: Map<String, Float> = expenses
        .groupBy { it.category }
        .mapValues { (_, list) -> list.sumOf { it.amount }.toFloat() }

    val entries = grouped.map { (category, amount) ->
        PieEntry(amount, category)
    }.toMutableList()

    val free = budget.toFloat() - totalSpent.toFloat()
    if (free > 0f) {
        entries.add(PieEntry(free, "Free"))
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Budget Breakdown") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) {
        AndroidView(
            factory = { context ->
                val pieChart = PieChart(context)

                val dataSet = PieDataSet(entries, "").apply {
                    valueTextSize = 14f
                    sliceSpace = 3f

                    // Assign colors: MATERIAL_COLORS + gray for "Free"
                    val colors = ColorTemplate.MATERIAL_COLORS.toMutableList()
                    if (entries.any { it.label == "Free" }) {
                        colors.add(android.graphics.Color.LTGRAY)
                    }
                    this.colors = colors
                }

                val pieData = PieData(dataSet).apply {
                    setValueFormatter(PercentFormatter(pieChart))
                    setValueTextSize(12f)
                }

                pieChart.apply {
                    data = pieData
                    setUsePercentValues(true)
                    description.isEnabled = false
                    centerText = "Total Budget\n$${budget}"
                    setEntryLabelTextSize(12f)
                    setEntryLabelColor(android.graphics.Color.BLACK)
                    legend.isEnabled = true
                    legend.textSize = 12f
                    invalidate()
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(400.dp)
                .padding(16.dp)
        )
    }
}
