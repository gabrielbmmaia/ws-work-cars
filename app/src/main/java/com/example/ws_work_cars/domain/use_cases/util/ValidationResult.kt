package com.example.ws_work_cars.domain.use_cases.util

sealed class ValidationResult{

    class EmailError(val errorMessage: String) : ValidationResult()
    object EmailSuccess: ValidationResult()

    class NameError(val errorMessage: String) : ValidationResult()
    object NameSuccess: ValidationResult()

    class Success(val message: String) : ValidationResult()

}
