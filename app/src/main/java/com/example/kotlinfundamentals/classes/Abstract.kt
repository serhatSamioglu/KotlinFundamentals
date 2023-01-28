package com.example.kotlinfundamentals.classes

/*
* An abstract class, member, or member function is created with the abstract keyword.
* If a class inherits from an abstract class,
* it must implement all its abstract members and member functions.
* We cannot create an instance from an abstract class.
* Abstract classes are implicitly open,
* since they are useless if they don't have any concrete subclasses.
*/

fun main() {
    val teacher = Engineer("Serhat")
    teacher.displayAge()
}

abstract class Person(name: String) {
    init {
        println("Abstract class created. Person name is $name")
    }

    abstract fun displayAge()
}

class Engineer(name: String): Person(name) {
    private val age: Int = 24

    override fun displayAge() {
        println("Non-abstract class. Age is $age")
    }
}