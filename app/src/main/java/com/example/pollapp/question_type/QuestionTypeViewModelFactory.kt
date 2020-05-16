package com.example.pollapp.question_type

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.pollapp.database.QuestionTypeDatabaseDao

class QuestionTypeViewModelFactory(private val database: QuestionTypeDatabaseDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(QuestionTypeViewModel::class.java)) {
            return QuestionTypeViewModel(database) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}