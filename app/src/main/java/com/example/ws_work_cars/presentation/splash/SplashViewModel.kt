package com.example.ws_work_cars.presentation.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ws_work_cars.domain.use_cases.SendLeadRoutineUseCase
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class SplashViewModel(
    private val sendLeadRoutine: SendLeadRoutineUseCase
) : ViewModel() {

    init {
        sendLeadRoutine()
    }

    private fun sendLeadRoutine() {

        viewModelScope.launch(IO) {

            sendLeadRoutine.execute()

        }

    }

}