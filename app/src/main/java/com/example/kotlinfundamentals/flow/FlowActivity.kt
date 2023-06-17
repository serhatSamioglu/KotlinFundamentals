package com.example.kotlinfundamentals.flow

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.kotlinfundamentals.databinding.ActivityFlowBinding
import kotlinx.coroutines.launch

class FlowActivity : AppCompatActivity() {

    lateinit var binding: ActivityFlowBinding
    lateinit var viewModel: FlowViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFlowBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this)[FlowViewModel::class.java]

        binding.buttonStateFlow.setOnClickListener {
            viewModel.triggerStateFlow()
        }

        binding.buttonSharedFlow.setOnClickListener {
            viewModel.triggerSharedFlow()
        }

        binding.buttonChannel.setOnClickListener {
            viewModel.triggerChannel()
        }

        handleObservers()
    }

    private fun handleObservers() {
        // you can reach StateFlow value with "viewModel.stateFlow.value"

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.stateFlow.collect {
                    binding.textViewStateFlow.text = it
                }
            }
        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.sharedFlow.collect {
                    binding.textViewSharedFlow.text = it
                }
            }
        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.channelFlow.collect {
                    binding.textViewChannel.text = it
                }
            }
        }
    }
}