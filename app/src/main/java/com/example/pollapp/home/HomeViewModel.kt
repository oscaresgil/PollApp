package com.example.pollapp.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pollapp.database.QuestionDatabaseDao

class HomeViewModel(val database: QuestionDatabaseDao) : ViewModel() {

    val questionCount = database.getQuestionCount()

    private val _startRegister = MutableLiveData<Boolean>()
    val startRegister: LiveData<Boolean>
        get() = _startRegister

    fun start() {
        _startRegister.value = questionCount.value ?: 0 > 0
    }

    fun startComplete() {
        _startRegister.value = false
    }

}
