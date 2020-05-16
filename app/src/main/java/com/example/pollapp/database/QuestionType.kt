package com.example.pollapp.database

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "question_type_table")
data class QuestionType(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var typeId: Long = 0L,

    @NonNull
    val type: String
) {
    override fun toString(): String {
        return type
    }
}