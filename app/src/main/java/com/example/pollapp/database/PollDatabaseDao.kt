package com.example.pollapp.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface PollDatabaseDao {

    @Insert
    fun insert(question: Question)

    @Update
    fun update(question: Question)

    @Query("SELECT * FROM question_table WHERE questionId = :key")
    fun getQuestion(key: Long): Question?

    @Query("SELECT * FROM question_table")
    fun getQuestions(): LiveData<List<Question>>

    @Query("SELECT COUNT(*) FROM question_table")
    fun getQuestionCount(): LiveData<Int>

}