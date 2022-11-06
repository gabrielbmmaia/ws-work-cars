package com.example.ws_work_cars.domain.useCases

import com.example.ws_work_cars.domain.useCases.util.ValidationResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * Classe para validação de um Lead
 * */

class LeadValidationUseCase(
    private val emailValidationUseCase: EmailValidationUseCase,
    private val nameValidationUseCase: NameValidationUseCase
) {

    suspend operator fun invoke(email: String, name: String): Flow<ValidationResult> = flow {

        /**
         * Validações
         * */

        val emailValidation = emailValidationUseCase(email)
        val nameValidation = nameValidationUseCase(name)

        /**
         * Se o e-email for válido será emtido um EmailSuccess,
         * caso contrário será emitido um EmailError
         * */

        if (emailValidation.successful) {
            emit(ValidationResult.EmailSuccess)

        } else {
            emit(ValidationResult.EmailError(emailValidation.errorMessage!!))
        }

        /**
         * Se o nome for válido será emtido um NameSuccess,
         * caso contrário será emitido um NameError
         * */

        if (nameValidation.successful) {
            emit(ValidationResult.NameSuccess)

        } else {
            emit(ValidationResult.NameError(nameValidation.errorMessage!!))
        }

        /**
         * Caso ambos sejam válidos, será emtido um Success
         * */

        if (emailValidation.successful && nameValidation.successful) {
            emit(ValidationResult.Success("$name, seus dados foram enviados"))
        }
    }
}
