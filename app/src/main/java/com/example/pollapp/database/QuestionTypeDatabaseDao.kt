package com.example.pollapp.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface QuestionTypeDatabaseDao {

    @Insert
    fun insert(questionType: QuestionType)

    @Update
    fun update(question: QuestionType)

    @Query("SELECT * FROM question_type_table WHERE id = :key")
    fun getQuestionType(key: Long): QuestionType?

    @Query("SELECT * FROM question_type_table")
    fun getQuestionTypes(): LiveData<List<QuestionType>>

    @Query("SELECT COUNT(*) FROM question_type_table")
    fun getQuestionTypeCount(): LiveData<Int>

}