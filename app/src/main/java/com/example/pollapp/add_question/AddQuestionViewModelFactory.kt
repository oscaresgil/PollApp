package com.example.pollapp.add_question

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.pollapp.database.PollDatabaseDao
import com.example.pollapp.question.AddQuestionViewModel

class AddQuestionViewModelFactory(private val database: PollDatabaseDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AddQuestionViewModel::class.java)) {
            return AddQuestionViewModel(database) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}