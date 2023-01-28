package com.example.kotlinfundamentals.classes
/*
A nested class cannot access the members of the outer class.
*/
fun main() {
    val child = Root.Child()
    println(child.displayMessage())
}

class Root {
    class Child {
        fun displayMessage() = "message"
    }
}