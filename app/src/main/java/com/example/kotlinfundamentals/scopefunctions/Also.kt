package com.example.kotlinfundamentals.scopefunctions

// It is used when we have to perform additional operations on already initialized object members.

fun main() {
    val names = mutableListOf("Serhat")

    names.also { println("names list elements before adding new name: $it") }
        .add("Sami")
}