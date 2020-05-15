package com.example.pollapp.add_question_type

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pollapp.database.PollDatabaseDao
import com.example.pollapp.database.QuestionType
import kotlinx.coroutines.*

class AddQuestionTypeViewModel(val database: PollDatabaseDao) : ViewModel() {

    val text = MutableLiveData<String>()

    private val viewModelJob = Job()

    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    fun insertQuestionType() {
        uiScope.launch {
            insert()
        }
    }

    private suspend fun insert(){
        withContext(Dispatchers.IO) {
            database.insertType(QuestionType(text = text.value ?: ""))
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}
