package com.applications.toms.usecases

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onEach


class StartTimerUseCase() {

    suspend operator fun invoke(totalSeconds: Long, index: Int) = flow {
        (totalSeconds downTo -1000).asFlow().onEach {
            delay(1000) //Un segundo de espera
        }.conflate().collect {
            emit(index to it)
        }
    }

}
