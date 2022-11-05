package com.example.ws_work_cars.domain.di

import com.example.ws_work_cars.domain.use_cases.*
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
     * A dependencia de repository está vindo do DataModule
     * */

    private fun useCaseModule(): Module {

        return module {
            factory { GetCarListUseCase(repository = get()) }

            factory { SaveLeadUseCase(repository = get()) }

            factory { EmailValidationUseCase () }

            factory { NameValidationUseCase () }

            factory { LeadValidationUseCase(emailValidationUseCase = get(), nameValidationUseCase = get()) }
        }
    }
}