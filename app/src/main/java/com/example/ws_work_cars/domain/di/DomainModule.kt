package com.example.ws_work_cars.domain.di

import com.example.ws_work_cars.domain.useCases.*
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module

object DomainModule {

    /**
     * Função load necessária para enviar para o Application todos os módulos de uma vez
     * */

    fun load() {
        loadKoinModules(useCaseModule())
    }

    /**
     * Módulo com todas as dependencias dos Use_Cases
     * */

    private fun useCaseModule(): Module {

        return module {
            factory { GetCarListUseCase(repository = get()) }

            factory { SaveLeadUseCase(repository = get()) }

            factory { EmailValidationUseCase() }

            factory { NameValidationUseCase() }

            factory {
                LeadValidationUseCase(
                    emailValidationUseCase = get(),
                    nameValidationUseCase = get()
                )
            }
            factory { SendLeadRoutineUseCase(repository = get()) }

            factory {
                LeadUseCase(
                    leadValidation = get(),
                    saveLead = get(),
                    sendLeadRoutine = get()
                )
            }
        }
    }
}
