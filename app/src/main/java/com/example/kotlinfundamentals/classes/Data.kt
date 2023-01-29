package com.example.kotlinfundamentals.classes

/*
Some classes are designed to hold data. With data classes,
we can considerably reduce the boilerplate code.
Compiler automatically creates the equals, hashCode, toString, and copy functions.
*/

fun main() {
    val user = User("Serhat", 24)

    // Here we call the toString method, which has been created for us.
    val (name, age) = user
    println("$name $age")
}

data class User(val name: String, var age: Int)