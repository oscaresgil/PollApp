package com.example.pollapp.add_question_type

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pollapp.database.QuestionType
import com.example.pollapp.database.QuestionTypeDatabaseDao
import kotlinx.coroutines.*

class AddQuestionTypeViewModel(val database: QuestionTypeDatabaseDao) : ViewModel() {

    val type = MutableLiveData<String>()

    val iconIndex = MutableLiveData<Int>()

    private val viewModelJob = Job()

    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    fun insertQuestionType(index: Int) {
        iconIndex.value = index
        uiScope.launch {
            insert()
        }
    }

    private suspend fun insert(){
        withContext(Dispatchers.IO) {
            database.insert(QuestionType(type = type.value ?: "", iconIndex = iconIndex.value ?: 0))
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}
