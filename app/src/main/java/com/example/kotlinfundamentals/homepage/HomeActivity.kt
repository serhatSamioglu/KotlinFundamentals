package com.example.kotlinfundamentals.homepage

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.kotlinfundamentals.coroutines.CoroutinesActivity
import com.example.kotlinfundamentals.databinding.ActivityHomeBinding
import com.example.kotlinfundamentals.flow.FlowActivity

class HomeActivity : AppCompatActivity() {

    lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        handleClickListeners()
    }

    private fun handleClickListeners() {
        binding.buttonCoroutines.setOnClickListener {
            startActivity(Intent(this, CoroutinesActivity::class.java))
        }

        binding.buttonFlow.setOnClickListener {
            startActivity(Intent(this, FlowActivity::class.java))
        }
    }
}