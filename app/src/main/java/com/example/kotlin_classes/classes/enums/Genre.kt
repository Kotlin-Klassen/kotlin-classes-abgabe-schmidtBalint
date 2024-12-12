package com.example.kotlin_classes.classes.enums

import android.provider.MediaStore.Audio.Genres

enum class Genre(val description: String) {
    FICTION("Fictional stories and novels"),
    NONFICTION("Non Fictional stories and novels"),
    SCIENCE("Science books"),
    HISTORY("History books"),
    CHILDREN("Comics");

    // Funktion, die die Beschreibung der jeweiligen Jahreszeit ausgibt
    fun printDescription() {
        println(description)
    }
}

// Funktion, die je nach Jahreszeit eine spezifische Nachricht ausgibt
fun handleGenre(genre: Genre) {
    when (genre) {
        Genre.FICTION -> println("It's FICTION: time to read Star Wars")
        Genre.NONFICTION -> println("It's NON FICTION: Time to read about finance.")
        Genre.SCIENCE -> println("It's SCIENCE: Time to read about the universe.")
        Genre.HISTORY -> println("It's HISTORY: Time to read about the ancient world.")
        Genre.CHILDREN -> println("It's CHILDREN: Time for children's books!")
    }
}

fun main() {
    val currentGenre = Genre.FICTION
    currentGenre.printDescription()
    handleGenre(currentGenre)
}