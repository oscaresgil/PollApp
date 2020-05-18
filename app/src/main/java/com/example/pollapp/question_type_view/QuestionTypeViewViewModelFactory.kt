package com.example.pollapp.question_type_view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.pollapp.database.QuestionTypeDatabaseDao

class QuestionTypeViewViewModelFactory(private val database: QuestionTypeDatabaseDao, private val typeId: Long) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(QuestionTypeViewViewModel::class.java)) {
            return QuestionTypeViewViewModel(database, typeId) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}