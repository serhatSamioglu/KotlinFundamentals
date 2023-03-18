package com.example.kotlinfundamentals.scopefunctions

// According to the Kotlin docs "with can be read as (with this object, do the following.)".

fun main() {
    val human = Human("Serhat", 25)

    with(human) {
        print("Name length is : ${name.length}")
        print("Human age is : $age")
    }
}

data class Human(var name: String, var age: Int)