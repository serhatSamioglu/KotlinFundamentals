package com.example.kotlinfundamentals.classes
/*
Kotlin classes are final by default.
Other classes cannot inherit from a final class.
To make a class inheritable, we mark it with the open keyword.
*/
fun main() {
    val dog = Dog()
    dog.isOpenClass(false)
    dog.printMessage()

    val animal = Animal()
    animal.isOpenClass()
    animal.printMessage()
}

open class Animal {

    open fun isOpenClass(isOpen: Boolean = true) {
        println(isOpen)
    }

    fun printMessage() = println("message")
}

class Dog: Animal() {

    override fun isOpenClass(isOpen: Boolean) {
        super.isOpenClass(isOpen)
        println("override open fun")
    }
}