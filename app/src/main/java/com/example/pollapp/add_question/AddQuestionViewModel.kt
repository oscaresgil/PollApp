package com.example.pollapp.add_question

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pollapp.database.QuestionDatabaseDao
import com.example.pollapp.database.Question
import com.example.pollapp.database.QuestionType
import com.example.pollapp.database.QuestionTypeDatabaseDao
import kotlinx.coroutines.*

class AddQuestionViewModel(val database: QuestionDatabaseDao,
                           val databaseType: QuestionTypeDatabaseDao) : ViewModel() {

    val text = MutableLiveData<String>()

    val typesList = databaseType.getQuestionTypes()

    private val viewModelJob = Job()

    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    fun insertQuestion(type: Any) {
        uiScope.launch {
            insert(type as QuestionType)
        }
    }

    private suspend fun insert(questionType: QuestionType?) {
        withContext(Dispatchers.IO) {
            database.insert(Question(text = text.value ?: "", type_id = questionType?.typeId ?: 0L))
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}
