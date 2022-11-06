package com.example.ws_work_cars.presentation.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ws_work_cars.domain.useCases.LeadUseCase
import com.example.ws_work_cars.domain.useCases.SendLeadRoutineUseCase
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class SplashViewModel(
    private val leadUseCase: LeadUseCase
) : ViewModel() {

    init {
        sendLeadRoutine()
    }

    /**
     * Controle de envio de leads
     * */

    private fun sendLeadRoutine() {
        viewModelScope.launch(IO) {
            leadUseCase.sendLeadRoutine.execute()
        }
    }
}
