package com.example.ws_work_cars

import android.app.Application
import com.example.ws_work_cars.data.di.DataModule
import com.example.ws_work_cars.domain.di.DomainModule
import com.example.ws_work_cars.presentation.di.PresentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        /**
         * Iniciando koin no aplicativo
         * */

        startKoin {
            androidContext(this@App)
        }

        /**
         * Carregamento de módulos do koin
         * */

        DataModule.load()
        DomainModule.load()
        PresentationModule.load()
    }
}
