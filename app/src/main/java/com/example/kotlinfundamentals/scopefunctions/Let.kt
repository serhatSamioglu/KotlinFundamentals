package com.example.kotlinfundamentals.scopefunctions
/*
The most common usage is null checks with safe call operator(?.)
"let" operation is performed on a object and return last statement in "let" block.
If there is no statement, it will return Unit by default like calling a function that has no return value.*/

fun main() {
    val animal = Animal("Max")

    animal.name?.let {
        print("The name of the Animal is: $it")
        // return unit by default because there is no return value.
    }

    val animalName = animal.name?.let {
        "The name of the Animal is: $it" // return this string.
    }
    print(animalName)
}

data class Animal(val name: String?)