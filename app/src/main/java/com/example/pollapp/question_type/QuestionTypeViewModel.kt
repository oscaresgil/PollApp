package com.example.pollapp.question_type

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.pollapp.database.QuestionType
import com.example.pollapp.database.QuestionTypeDatabaseDao
import java.lang.StringBuilder

class QuestionTypeViewModel(val database: QuestionTypeDatabaseDao) : ViewModel() {

    val types = database.getQuestionTypes()

    private val _questionTypeClicked = MutableLiveData<Long>()
    val questionTypeClicked: LiveData<Long>
        get() = _questionTypeClicked

    fun onQuestionTypeClicked(typeId: Long) {
        _questionTypeClicked.value = typeId
    }

    fun onQuestionTypeClickedCompleted(){
        _questionTypeClicked.value = null
    }
}
