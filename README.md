# Table of Contents  
[Types of Kotlin classes](#TypesofKotlinclasses)

[Generics: in out type](#GenericsInOutType)

[Coroutines](#Coroutines)

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
That means functions can take M as arguments but they can't return M

When you declare a generic type with an out modifier,
That means functions can return T but they can't take T as arguments
*/
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
<a name="Coroutines"/>

## Coroutines
```ruby
/*
The Kotlin team defines coroutines as ???lightweight threads???
They are sort of tasks that the actual threads can execute

- Scopes in Kotlin Coroutines
1. Global Scope
Global Scope is one of the ways by which coroutines are launched.
When Coroutines are launched within the global scope, they live long as the application does.
If the coroutines finish it???s a job, it will be destroyed and will not keep alive until the application dies.
2. LifeCycle Scope
All the coroutines launched within the activity also dies when the activity dies.
3. ViewModel Scope
Coroutine in this scope will live as long the view model is alive.

- Start a coroutine
You can start coroutines in one of two ways:
1. Launch starts a new coroutine and doesn't return the result to the caller.
Any work that is considered "fire and forget" can be started using launch.
2. Async starts a new coroutine and allows you to return a result with a suspend function called await.

- Kotlin coroutines use dispatchers to determine which threads are used for coroutine execution.
1. Dispatchers.Main: It is mostly used when we need to perform the UI operations within the coroutine.
2. Dispatchers.IO: This dispatcher is optimized to perform disk or network I/O outside of the main thread.
3. Dispatchers.Default: This dispatcher is optimized to perform CPU-intensive work outside of the main thread.
Note: Also context could switch easily with the help of withContext function.
*/
class CoroutinesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coroutines)

        lifecycleScope.launch(Dispatchers.IO) {
            Log.d("testThread1", Thread.currentThread().name) // testThread1: DefaultDispatcher-worker-1
            val resultOfNetworkCall = simulateNetworkCall()
            withContext(Dispatchers.Main) {
                Log.d("testThread2", Thread.currentThread().name) // testThread2: main
                // update ui here
            }
        }

        lifecycleScope.launch(Dispatchers.IO) {
            val networkCallDeferred = async { simulateNetworkCall() }
            val networkCallResult = networkCallDeferred.await()
        }
    }

    private suspend fun simulateNetworkCall(): String {
        delay(3000L)
        return "Network answer"
    }
}
```