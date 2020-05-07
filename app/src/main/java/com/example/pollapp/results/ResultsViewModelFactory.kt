package com.example.pollapp.results

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.pollapp.data.Question

class ResultsViewModelFactory(private val questions: MutableList<Question>) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ResultsViewModel::class.java)) {
            return ResultsViewModel(questions) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}