package com.example.pollapp.poll

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.pollapp.database.QuestionDatabaseDao

class PollViewModelFactory(private val database: QuestionDatabaseDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PollViewModel::class.java)) {
            return PollViewModel(database) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}