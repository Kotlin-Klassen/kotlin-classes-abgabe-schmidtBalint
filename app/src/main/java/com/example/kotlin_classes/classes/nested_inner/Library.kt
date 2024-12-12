package com.example.kotlin_classes.classes.nested_inner

import com.example.kotlin_classes.classes.data.Book
import com.example.kotlin_classes.classes.sealed_class.*

// Äußere Klasse für eine Bibliothek
class Library(val libraryName: String) {

    // Sammlung der Bücher
    private val books = mutableListOf<Book>()

    // Nested Class: Speichert die Adresse der Bibliothek
    class LibraryAddress(val street: String, val city: String, val zipCode: String) {
        fun printAddress() = "Address: $street, $city, $zipCode"
    }

    // Inner Class: Hat Zugriff auf die Mitglieder der äußeren Klasse Library
    inner class Librarian(val name: String) {
        fun getLibrarianInfo() = "Librarian: $name, Library: $libraryName"
    }

    // Inner Class: Repräsentiert ein Mitglied der Bibliothek
    inner class LibraryMember(val name: String, val memberID: String) {
        fun checkoutBook(book: Book, dueDate: String) {
            if (book.inStock) {
                book.inStock = false
                val status = CheckedOut(dueDate)
                println("$name checked out '${book.title}'.")
                processStatus(status)
            } else {
                println("'${book.title}' is not available for checkout.")
            }
        }

        fun reserveBook(book: Book) {
            if (book.inStock) {
                val status = Reserved(name)
                println("$name reserved '${book.title}'.")
                processStatus(status)
            } else {
                println("'${book.title}' is not available for reservation.")
            }
        }
    }

    // Methoden der Library-Klasse

    // Hinzufügen eines neuen Buches
    fun addBook(book: Book) {
        books.add(book)
        println("Added book: ${book.title} by ${book.author}")
    }

    // Suchen eines Buches nach Titel oder Autor
    fun searchBook(query: String): List<Book> {
        return books.filter { it.title.contains(query, ignoreCase = true) || it.author.contains(query, ignoreCase = true) }
    }

    // Anzeigen des aktuellen Status aller Bücher
    fun displayBooks() {
        if (books.isEmpty()) {
            println("No books in the library.")
        } else {
            books.forEach {
                val status = if (it.inStock) Available else "Not Available"
                println("Book Title: ${it.title}, Author: ${it.author}, Status: $status")
            }
        }
    }
}

fun main() {
    // Erstellen einer Library-Instanz
    val myLibrary = Library("City Library")

    // Hinzufügen von Büchern
    val book1 = Book("Kotlin Programming", "John Doe", 29.99, true)
    val book2 = Book("Advanced Kotlin", "Jane Smith", 35.99, true)
    myLibrary.addBook(book1)
    myLibrary.addBook(book2)

    // Adresse der Bibliothek hinzufügen und ausgeben
    val address = Library.LibraryAddress("123 Main St", "Springfield", "12345")
    println(address.printAddress())

    // Bibliothekar erstellen
    val librarian = myLibrary.Librarian("Alice Smith")
    println(librarian.getLibrarianInfo())

    // Mitglied erstellen
    val member = myLibrary.LibraryMember("Bob Johnson", "M001")

    // Mitglied leiht ein Buch aus und reserviert ein anderes
    member.checkoutBook(book1, "2024-01-01")
    member.reserveBook(book2)

    // Aktuellen Status der Bücher anzeigen
    myLibrary.displayBooks()

    // Nach einem Buch suchen
    val searchResults = myLibrary.searchBook("Kotlin")
    println("Search Results:")
    searchResults.forEach { println("Book Title: ${it.title}, Author: ${it.author}, Price: ${it.price}, In Stock: ${it.inStock}") }
}
