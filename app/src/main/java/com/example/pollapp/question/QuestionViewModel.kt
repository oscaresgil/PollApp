package com.example.pollapp.question

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pollapp.data.Question

class QuestionViewModel : ViewModel() {
    val question = MutableLiveData<Question>()

    fun updateQuestion(question: Question){
        this.question.value = question
    }

}
