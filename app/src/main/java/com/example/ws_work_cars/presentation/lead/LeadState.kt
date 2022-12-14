package com.example.ws_work_cars.presentation.lead

sealed class LeadState {

    object Initial : LeadState()
    class Success(val message: String? = null) : LeadState()
    class Error(val errorMessage: String) : LeadState()

}
