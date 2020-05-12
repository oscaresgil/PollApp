package com.example.pollapp.poll

import androidx.lifecycle.*
import com.example.pollapp.database.PollDatabaseDao
import com.example.pollapp.database.Question
import kotlinx.coroutines.*

class PollViewModel(val database: PollDatabaseDao) : ViewModel() {

    val questions = database.getQuestions()

    private val _registerComplete = MutableLiveData<Boolean>()
    val registerComplete: LiveData<Boolean>
        get() = _registerComplete

    var questionCount = 1
        private set

    var totalCount = 0
        private set

    val currentQuestion = MutableLiveData<Question>()

    private val viewModelJob = Job()

    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    fun initialize(questions: List<Question>) {
        totalCount = questions.size
        if (questions.isEmpty()) {
            _registerComplete.value = true
        } else {
            currentQuestion.value = questions[0]
        }
    }

    fun updateCurrentQuestion() {
        val question = currentQuestion.value
        questionCount ++
        if (totalCount >= questionCount) {
            currentQuestion.value = questions.value?.get(questionCount - 1)
        } else {
            _registerComplete.value = true
        }
        uiScope.launch {
            update(question)
        }
    }

    private suspend fun update(question: Question?) {
        withContext(Dispatchers.IO) {
            question ?.let {
//                it.answer = answer.value ?: ""
                database.update(it)
            }
        }
    }

    fun finishRegister() {
        _registerComplete.value = false
        questionCount = 1
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

}
