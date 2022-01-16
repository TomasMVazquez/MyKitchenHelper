package com.applications.toms.mykitchenhelper.ui.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.applications.toms.usecases.StartTimerUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class TimerViewModel: ViewModel() {

    private val timerUseCase = StartTimerUseCase()

    private val mapTimers = mutableMapOf<Int, Long>()

    private val _state : MutableStateFlow<List<Long>> = MutableStateFlow(listOf())
    val state: StateFlow<List<Long>> get() = _state

    private var index = 0

    //Mandamos tiempo total e indice del mapa. Cada flow actualiza un unico indice y emitimos estado
    fun start(totalSeconds: Long) {
        viewModelScope.launch {
            index++
            timerUseCase.invoke(totalSeconds, index).collect { //sustituir por tiempo elegido
                mapTimers[it.first] = it.second
                _state.emit(mapTimers.values.toList())
            }
        }
    }

}