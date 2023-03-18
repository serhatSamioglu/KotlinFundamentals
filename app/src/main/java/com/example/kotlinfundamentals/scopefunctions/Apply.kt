package com.example.kotlinfundamentals.scopefunctions

import android.content.Intent
/*
The most common use case for apply is for object configuration.
Apply returns the context object itself.*/

fun main() {
    val intent = Intent().apply {
        putExtra("uri", "https://www.linkedin.com/in/serhat-%C5%9Famio%C4%9Flu-2945b2173/")
    }
    // startActivity(intent)
}