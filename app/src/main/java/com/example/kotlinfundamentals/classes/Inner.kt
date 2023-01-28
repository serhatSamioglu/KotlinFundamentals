package com.example.kotlinfundamentals.classes
/*
Unlike nested classes, they can access the members of their outer classes.
*/
fun main() {
    val innerClass = Outer().Inner()
    innerClass.displayOuterClassMessage()
}

class Outer {
    private val message = "Outer class message"

    inner class Inner {
        fun displayOuterClassMessage() = println(message)
    }
}