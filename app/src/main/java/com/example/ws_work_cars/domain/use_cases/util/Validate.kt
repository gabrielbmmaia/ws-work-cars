package com.example.ws_work_cars.domain.use_cases.util

data class Validate(
    val successful: Boolean,
    val errorMessage: String? = null
)