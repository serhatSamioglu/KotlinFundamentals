package com.example.kotlinfundamentals.scopefunctions

// Equivalent to 'apply', but it returns the last line.

fun main() {
    val person = Person(24)

    val age = person.run {
        age = 25
        return@run age // you do not have to write return@run
    }
}

data class Person(var age: Int)