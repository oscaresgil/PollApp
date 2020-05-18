package com.example.pollapp.question_type_view

import androidx.lifecycle.ViewModel
import com.example.pollapp.database.QuestionTypeDatabaseDao
import kotlinx.coroutines.*

class QuestionTypeViewViewModel(val database: QuestionTypeDatabaseDao, val typeId: Long) : ViewModel() {

    val questionType = database.getQuestionType(typeId)

    val viewModelJob = Job()

    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    fun deleteQuestionType(){
        uiScope.launch {
            delete()
        }
    }

    private suspend fun delete(){
        withContext(Dispatchers.IO) {
            questionType.value?.let { database.delete(it) }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}