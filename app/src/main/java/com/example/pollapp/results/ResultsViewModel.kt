package com.example.pollapp.results

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.pollapp.database.QuestionDatabaseDao
import com.example.pollapp.database.Question
import java.lang.StringBuilder

class ResultsViewModel(val database: QuestionDatabaseDao) : ViewModel() {

    private val questions = database.getQuestions()

    val questionCount = Transformations.map(questions) { it.size }

    val resultsText = Transformations.map(questions) {
        buildResultsText(it)
    }

    private val _restartRegister = MutableLiveData<Boolean>()
    val restartRegister: LiveData<Boolean>
        get() = _restartRegister

    fun restart() {
        _restartRegister.value = questionCount.value ?: 0 > 0
    }

    fun restartComplete() {
        _restartRegister.value = false
    }

    private fun buildResultsText(questions: List<Question>) : String{
        val resultsText = StringBuilder()
        for (question in questions){
            resultsText.append("Question ${question.questionId}: ${question.text} : ${question.answer}\n")
        }
        return resultsText.toString()
    }
}
