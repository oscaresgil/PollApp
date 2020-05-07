package com.example.pollapp.data

interface QuestionUser {
    var questions : MutableList<Question>

    fun initialize() {
        questions = mutableListOf(
            Question("¿Qué le pareció el servicio?"),
            Question("¿Qué le pareció la rapidez?"),
            Question("¿Qué sugerencias nos puede dar?"),
            Question("¿Estaría dispuesto a recomendarnos? ¿Porqué?")
        )
    }
}