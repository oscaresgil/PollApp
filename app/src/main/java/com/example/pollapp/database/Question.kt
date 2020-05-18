package com.example.pollapp.database

import androidx.annotation.NonNull
import androidx.room.*
import androidx.room.ForeignKey.SET_DEFAULT

@Entity(tableName = "question_table",
        foreignKeys = [
            ForeignKey(entity = QuestionType::class,
            parentColumns = ["id"],
            childColumns = ["type_id"],
            onDelete = SET_DEFAULT)
        ],
        indices = [
            Index(name = "typeId_index", value = ["type_id"])
        ])
data class Question(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var questionId: Long = 0L,

    @NonNull
    val text: String,

    var answer: String = "",

    var type_id: Long = 0L
)