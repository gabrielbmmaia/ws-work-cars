package com.example.ws_work_cars.domain.use_cases

import android.util.Patterns
import com.example.ws_work_cars.domain.use_cases.util.Validate

class EmailValidationUseCase {

    operator fun invoke(email: String): Validate {

        if (email.isBlank()) {

            return Validate(
                successful = false,
                errorMessage = "Digite um e-mail"
            )

        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {

            return Validate(
                successful = false,
                errorMessage = "Digite um e-mail válido"
            )

        }

        return Validate(
            successful = true
        )

//        if (email.isNotBlank() && Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
//
//            emit(ValidationResult.Success("Dados enviados com sucesso"))
//
//        }

    }

}