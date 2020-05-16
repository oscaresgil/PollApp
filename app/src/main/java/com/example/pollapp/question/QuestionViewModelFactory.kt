package com.example.pollapp.question

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.pollapp.database.QuestionDatabaseDao

class QuestionViewModelFactory(private val database: QuestionDatabaseDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(QuestionViewModel::class.java)) {
            return QuestionViewModel(database) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}