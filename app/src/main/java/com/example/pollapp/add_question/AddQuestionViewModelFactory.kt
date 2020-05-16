package com.example.pollapp.add_question

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.pollapp.database.QuestionDatabaseDao
import com.example.pollapp.database.QuestionTypeDatabaseDao

class AddQuestionViewModelFactory(private val database: QuestionDatabaseDao,
                                  private val databaseType: QuestionTypeDatabaseDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AddQuestionViewModel::class.java)) {
            return AddQuestionViewModel(database, databaseType) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}