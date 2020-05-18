package com.example.pollapp.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface QuestionTypeDatabaseDao {

    @Insert
    fun insert(questionType: QuestionType)

    @Update
    fun update(question: QuestionType)

    @Delete
    fun delete(question: QuestionType)

    @Query("SELECT * FROM question_type_table WHERE id = :key")
    fun getQuestionType(key: Long): LiveData<QuestionType>

    @Query("SELECT * FROM question_type_table")
    fun getQuestionTypes(): LiveData<List<QuestionType>>

    @Query("SELECT COUNT(*) FROM question_type_table")
    fun getQuestionTypeCount(): LiveData<Int>

}