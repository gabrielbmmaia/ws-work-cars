package com.example.ws_work_cars.domain.useCases.util

/**
 * Classe para auxiliar as validações
 * */

data class Validate(
    val successful: Boolean,
    val errorMessage: String? = null
)
