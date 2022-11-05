package com.example.ws_work_cars.domain.use_cases

import com.example.ws_work_cars.domain.use_cases.util.Validate

class NameValidationUseCase {

    operator fun invoke(name: String): Validate {

        if (name.isBlank()) {

            return Validate(
                successful = false,
                errorMessage = "Digite seu nome"
            )
        }

        return Validate(successful = true)

    }
}