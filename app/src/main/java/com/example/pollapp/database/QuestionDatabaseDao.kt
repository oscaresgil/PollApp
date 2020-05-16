package com.example.pollapp.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface QuestionDatabaseDao {

    @Insert
    fun insert(question: Question)

    @Update
    fun update(question: Question)

    @Query("SELECT * FROM question_table WHERE id = :key")
    fun getQuestion(key: Long): Question?

    @Query("SELECT * FROM question_table")
    fun getQuestions(): LiveData<List<Question>>

    @Query("SELECT COUNT(*) FROM question_table")
    fun getQuestionCount(): LiveData<Int>

    @Query("SELECT q.*, t.type FROM question_table q LEFT JOIN question_type_table t ON q.type_id = t.id")
    fun getQuestionsWithType(): LiveData<List<QuestionWithType>>

}