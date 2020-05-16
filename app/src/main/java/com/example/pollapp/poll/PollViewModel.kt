package com.example.pollapp.poll

import androidx.lifecycle.*
import com.example.pollapp.database.QuestionDatabaseDao
import com.example.pollapp.database.Question
import com.example.pollapp.database.QuestionWithType
import kotlinx.coroutines.*

class PollViewModel(val database: QuestionDatabaseDao) : ViewModel() {

    val questions = database.getQuestionsWithType()

    private val _registerComplete = MutableLiveData<Boolean>()
    val registerComplete: LiveData<Boolean>
        get() = _registerComplete

    var questionCount = 1
        private set

    var totalCount = 0
        private set

    val currentQuestion = MutableLiveData<QuestionWithType>()

    private val viewModelJob = Job()

    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    fun initialize(questions: List<QuestionWithType>) {
        totalCount = questions.size
        if (questions.isEmpty()) {
            _registerComplete.value = true
        } else {
            currentQuestion.value = questions[0]
        }
    }

    fun updateCurrentQuestion() {
        val questionWithType = currentQuestion.value
        questionCount ++
        if (totalCount >= questionCount) {
            currentQuestion.value = questions.value?.get(questionCount - 1)
        } else {
            _registerComplete.value = true
        }
        uiScope.launch {
            update(questionWithType?.let {
                Question(questionId = it.question.questionId,
                        text = it.question.text,
                        answer = it.question.answer,
                        type_id = it.question.type_id)
            })
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
