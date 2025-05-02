package com.example.smartbudgettracker.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.smartbudgettracker.data.db.ExpenseDatabase
import com.example.smartbudgettracker.data.model.Expense
import com.example.smartbudgettracker.data.repository.ExpenseRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class ExpenseViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: ExpenseRepository

    val expenses: StateFlow<List<Expense>>
    val totalSpent: StateFlow<Double>
    private val _budget = MutableStateFlow(1000.0)
    val budget: StateFlow<Double> = _budget

    init {
        val dao = ExpenseDatabase.getDatabase(application).expenseDao()
        repository = ExpenseRepository(dao)

        expenses = repository.allExpenses
            .map { it.sortedByDescending { it.timestamp } }
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

        totalSpent = expenses.map { list ->
            list.sumOf { it.amount }
        }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), 0.0)
    }

    fun addExpense(expense: Expense) {
        viewModelScope.launch {
            repository.insert(expense)
        }
    }

    fun deleteExpense(expense: Expense) {
        viewModelScope.launch {
            repository.delete(expense)
        }
    }

    fun setBudget(value: Double) {
        _budget.value = value
    }
}
