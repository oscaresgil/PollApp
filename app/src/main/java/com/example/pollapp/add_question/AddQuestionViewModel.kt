package com.example.pollapp.question

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pollapp.database.PollDatabaseDao
import com.example.pollapp.database.Question
import kotlinx.coroutines.*

class AddQuestionViewModel(val database: PollDatabaseDao) : ViewModel() {

    val text = MutableLiveData<String>()

    private val viewModelJob = Job()

    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    fun insertQuestion() {
        uiScope.launch {
            insert()
        }
    }

    private suspend fun insert(){
        withContext(Dispatchers.IO) {
            database.insert(Question(text = text.value ?: ""))
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}
