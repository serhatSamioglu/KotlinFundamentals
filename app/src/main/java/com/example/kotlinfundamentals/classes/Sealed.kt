package com.example.kotlinfundamentals.classes

fun main() {
    val circle = Shape.Circle(7f)
    val square = Shape.Square(5)
    val rectangle = Shape.Rectangle(8, 6)

    getArea(circle)
    getArea(square)
    getArea(rectangle)
}

sealed class Shape {
    class Circle(var radius: Float) : Shape()
    class Square(var width: Int) : Shape()
    class Rectangle(var width: Int, var height: Int) : Shape()
}

fun getArea(e: Shape) =
    when (e) {
        is Shape.Circle -> println("Circle area is ${Math.PI * e.radius * e.radius}")
        is Shape.Square -> println("Square area is ${e.width * e.width}")
        is Shape.Rectangle -> println("Rectangle area is ${e.width * e.height}")
    }

// Sealed classes can have subclasses, but they must either be in the same file

sealed class Shape2
class Circle2(var radius: Float) : Shape2()
class Square2(var width: Int) : Shape2()
class Rectangle2(var width: Int, var height: Int) : Shape2()

fun getArea2(e: Shape2) =
    when (e) {
        is Circle2 -> println("Circle area is ${Math.PI * e.radius * e.radius}")
        is Square2 -> println("Square area is ${e.width * e.width}")
        is Rectangle2 -> println("Rectangle area is ${e.width * e.height}")
    }