package com.example.kotlin_classes.classes.sealed_class

sealed class BookStatus

data class CheckedOut(val dueDate: String): BookStatus()
data class Reserved(val name:String): BookStatus()
object Available: BookStatus()

fun processStatus(status: BookStatus){
    when (status) {
        is CheckedOut -> println("Book is checked out until: ${status.dueDate}.\n")
        is Reserved -> println("Book is reserved by ${status.name}.\n")
        Available -> println("Book is available")
    }
}
fun main() {
    val statusCheckedOut = CheckedOut("19/12/2024")
    val statusReserved = Reserved("customer@example.com")
    val statusAvailable = Available

    processStatus(statusCheckedOut)
    processStatus(statusReserved)
    processStatus(statusAvailable)
}