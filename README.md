# Table of Contents  
[Types of Kotlin classes](#TypesofKotlinclasses)

[Generics: in out type](#GenericsInOutType)

...    
<a name="TypesofKotlinclasses"/>
## Types of Kotlin classes

### 1. Data Class
```ruby
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
```
Output -> Serhat 24

### 2. Open Class
```ruby
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
        println("isOpenClass: $isOpen")
    }

    fun printMessage() = println("message")
}

class Dog: Animal() {

    override fun isOpenClass(isOpen: Boolean) {
        super.isOpenClass(isOpen)
        println("override open fun")
    }
}
```
Output ->
isOpenClass: false
override open fun
message
isOpenClass: true
message

### 3. Abstract Class
```ruby
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
```
Output ->
Abstract class created. Person name is Serhat
Non-abstract class. Age is 24

### 4. Nested Class
```ruby
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
```

### 5. Inner Class
```ruby
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
```

### 6. Sealed Class
```ruby
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
```
<a name="GenericsInOutType"/>

## Generics: in out type
```ruby
/*
When you declare a generic type with an in modifier,
That means functions can take M as arguments but they can't return M:

When you declare a generic type with an out modifier,
That means functions can return T but they can't take T as arguments:*/
interface Production<in M, out T> {
    fun produceIn(input: M)
    fun produceOut(): T
}

class Product: Production<Int, String> {
    override fun produceIn(input: Int) {
        println(input.toString())
    }

    override fun produceOut(): String {
        return "produceOut"
    }
}

fun main() {
    val product = Product()
    product.produceIn(0)
    println(product.produceOut())
}
```
