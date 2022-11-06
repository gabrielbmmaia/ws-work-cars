package com.example.ws_work_cars.presentation.di

import com.example.ws_work_cars.presentation.home.HomeViewModel
import com.example.ws_work_cars.presentation.lead.LeadViewModel
import com.example.ws_work_cars.presentation.splash.SplashViewModel
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
     * Módulos das ViewModels do aplicativo.
     * */

    private fun viewModelModule(): Module {

        return module {

            factory { HomeViewModel(carList = get()) }

            factory { LeadViewModel(leadUseCase = get()) }

            factory { SplashViewModel(leadUseCase = get()) }
        }
    }
}
