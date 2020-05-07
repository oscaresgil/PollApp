package com.example.pollapp.results

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pollapp.data.Question

class ResultsViewModel(questions: MutableList<Question>) : ViewModel() {
    private val _questionCount = MutableLiveData<Int>()
    val questionCount: LiveData<Int>
        get() = _questionCount


    private val _resultsText = MutableLiveData<String>()
    val resultsText: LiveData<String>
        get() = _resultsText

    init {
        _questionCount.value = questions.size
        for (question in questions) {
            _resultsText.value += "${question.text} : ${question.answer}\n"
        }
    }
}
