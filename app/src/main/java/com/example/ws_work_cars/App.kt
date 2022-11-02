package com.example.ws_work_cars

import android.app.Application
import com.example.ws_work_cars.data.di.DataModule
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

    }
}