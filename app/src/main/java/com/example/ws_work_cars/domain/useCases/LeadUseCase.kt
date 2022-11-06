package com.example.ws_work_cars.domain.useCases

data class LeadUseCase(
    val leadValidation: LeadValidationUseCase,
    val saveLead: SaveLeadUseCase,
    val sendLeadRoutine: SendLeadRoutineUseCase
)
