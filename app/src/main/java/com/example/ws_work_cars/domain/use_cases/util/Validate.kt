package com.example.ws_work_cars.domain.use_cases.util

/**
 * Classe para auxiliar as validações
 * */

data class Validate(
    val successful: Boolean,
    val errorMessage: String? = null
)