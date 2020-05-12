package com.example.pollapp.question

import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.pollapp.database.PollDatabaseDao
import com.example.pollapp.database.Question
import java.lang.StringBuilder

class QuestionViewModel(val database: PollDatabaseDao) : ViewModel() {

    private val questions = database.getQuestions()

    val questionsText = Transformations.map(questions) {
        buildQuestionsText(it)
    }

    private fun buildQuestionsText(questions: List<Question>) : String{
        val questionsText = StringBuilder()
        for (question in questions){
            questionsText.append("Question: ${question.questionId}\nText: ${question.text}\nAnswer: ${question.answer}\n\n")
        }
        return questionsText.toString()
    }
}
