package com.example.pollapp.question_type

import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.pollapp.database.PollDatabaseDao
import com.example.pollapp.database.QuestionType
import java.lang.StringBuilder

class QuestionTypeViewModel(val database: PollDatabaseDao) : ViewModel() {

    private val types = database.getQuestionTypes()

    val typesText = Transformations.map(types) {
        buildQuestionsText(it)
    }

    private fun buildQuestionsText(types: List<QuestionType>) : String{
        val typesText = StringBuilder()
        for (type in types){
            typesText.append("QuestionType: ${type.typeId}\nText: ${type.text}\n\n")
        }
        return typesText.toString()
    }
}
