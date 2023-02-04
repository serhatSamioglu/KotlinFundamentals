package com.example.kotlinfundamentals.generics

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