package com.example.pollapp.results

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.pollapp.database.PollDatabaseDao

class ResultsViewModelFactory(private val database: PollDatabaseDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ResultsViewModel::class.java)) {
            return ResultsViewModel(database) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}