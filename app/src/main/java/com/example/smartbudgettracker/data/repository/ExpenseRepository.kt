package com.example.smartbudgettracker.data.repository

import com.example.smartbudgettracker.data.db.ExpenseDao
import com.example.smartbudgettracker.data.model.Expense
import kotlinx.coroutines.flow.Flow

class ExpenseRepository(private val dao: ExpenseDao) {

    val allExpenses: Flow<List<Expense>> = dao.getAllExpenses()

    suspend fun insert(expense: Expense) {
        dao.insertExpense(expense)
    }

    suspend fun delete(expense: Expense) {
        dao.deleteExpense(expense)
    }
}
