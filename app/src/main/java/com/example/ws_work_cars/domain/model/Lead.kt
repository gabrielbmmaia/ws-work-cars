package com.example.ws_work_cars.domain.model

/**
 * Modelo Lead utilizado na camada de View e ViewModel
 * */

data class Lead(
    val carId: Long,
    val nomeLead: String,
    val emailLead: String
)
