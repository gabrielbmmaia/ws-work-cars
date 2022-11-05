package com.example.ws_work_cars.domain.use_cases

import com.example.ws_work_cars.domain.use_cases.util.Validate

/**
 * Classe para validar se um nome é valido.
 * Ele será válido quando não for vazio
 * */

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