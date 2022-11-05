package com.example.ws_work_cars.presentation.di

import com.example.ws_work_cars.presentation.home.HomeViewModel
import com.example.ws_work_cars.presentation.lead_details.LeadViewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module

object PresentationModule {

    /**
     * Função load necessária para enviar para o Application todos os módulos de uma vez
     * */

    fun load() {
        loadKoinModules(viewModelModule())
    }


    /**
     * A dependencia de carList está vindo do DomainModule
     * */

    private fun viewModelModule(): Module {

        return module {

            factory { HomeViewModel(carListUseCase = get(), repository = get()) }

            factory { LeadViewModel(saveLeadUseCase = get(), leadValidation = get()) }
        }
    }
}