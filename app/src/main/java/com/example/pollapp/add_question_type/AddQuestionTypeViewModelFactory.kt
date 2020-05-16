package com.example.pollapp.add_question_type

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.pollapp.database.QuestionTypeDatabaseDao

class AddQuestionTypeViewModelFactory(private val database: QuestionTypeDatabaseDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AddQuestionTypeViewModel::class.java)) {
            return AddQuestionTypeViewModel(database) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}