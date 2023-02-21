package com.example.kotlinfundamentals.coroutines

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import com.example.kotlinfundamentals.R
import kotlinx.coroutines.*

/*
The Kotlin team defines coroutines as “lightweight threads”
They are sort of tasks that the actual threads can execute

- Scopes in Kotlin Coroutines
1. Global Scope
Global Scope is one of the ways by which coroutines are launched.
When Coroutines are launched within the global scope, they live long as the application does.
If the coroutines finish it’s a job, it will be destroyed and will not keep alive until the application dies.
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