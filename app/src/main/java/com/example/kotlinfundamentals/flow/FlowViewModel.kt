package com.example.kotlinfundamentals.flow

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class FlowViewModel: ViewModel() {
    // StateFlow is a state-holder observable flow.
    // With the help of this feature, you can reach the state with .value property.
    private val _stateFlow = MutableStateFlow("Default StateFlow Value")
    val stateFlow = _stateFlow.asStateFlow()

    // SharedFlow does not keep the state so the best use case for this flow is one-time events.
    // SharedFlow emits value even if there is no collector because It is a hot flow.
    // Also, emitted values could collect from all its collectors.
    private val _sharedFlow = MutableSharedFlow<String>()
    val sharedFlow = _sharedFlow.asSharedFlow()

    // Channel does not keep the state like SharedFlow.
    // Again, the best use case for this flow is one-time events.
    // But Channel is cold flow so it does not send value if there is no collector.
    // Also, the channel could not collectable from different collectors.
    private val _channelFlow = Channel<String>()
    val channelFlow = _channelFlow.receiveAsFlow()

    fun triggerStateFlow() {
        viewModelScope.launch {
            for (i in 1..7) {
                delay(1000L)
                _stateFlow.value = i.toString()
            }
        }
    }

    fun triggerSharedFlow() {
        viewModelScope.launch {
            for (i in 1..7) {
                delay(1000L)
                _sharedFlow.emit(i.toString())
            }
        }
    }

    fun triggerChannel() {
        viewModelScope.launch {
            for (i in 1..7) {
                delay(1000L)
                _channelFlow.send(i.toString())
            }
        }
    }
}

