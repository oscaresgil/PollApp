package com.example.pollapp.question

import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.pollapp.database.QuestionDatabaseDao
import com.example.pollapp.database.QuestionWithType
import java.lang.StringBuilder

class QuestionViewModel(val database: QuestionDatabaseDao) : ViewModel() {

    private val questions = database.getQuestionsWithType()

    val questionsText = Transformations.map(questions) {
        buildQuestionsText(it)
    }

    private fun buildQuestionsText(questionsWithType: List<QuestionWithType>) : String{
        val questionsText = StringBuilder()
        for (qwt in questionsWithType){
            questionsText.append("Question: ${qwt.question.questionId}\n" +
                    "Text: ${qwt.question.text}\n" +
                    "Answer: ${qwt.question.answer}\n" +
                    "Type: ${qwt.type}\n\n")
        }
        return questionsText.toString()
    }
}
