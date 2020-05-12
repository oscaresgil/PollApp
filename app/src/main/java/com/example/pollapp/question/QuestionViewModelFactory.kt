package com.example.pollapp.question

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.pollapp.database.PollDatabaseDao

class QuestionViewModelFactory(private val database: PollDatabaseDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(QuestionViewModel::class.java)) {
            return QuestionViewModel(database) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}