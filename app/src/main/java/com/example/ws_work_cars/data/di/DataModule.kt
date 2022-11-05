package com.example.ws_work_cars.data.di

import android.util.Log
import com.example.ws_work_cars.core.Constants.BASE_URL
import com.example.ws_work_cars.core.Constants.OK_HTTP
import com.example.ws_work_cars.data.local.database.LeadDatabase
import com.example.ws_work_cars.data.remote.CarService
import com.example.ws_work_cars.data.repository.CarRepositoryImpl
import com.example.ws_work_cars.data.repository.LeadRepositoryImpl
import com.example.ws_work_cars.domain.repository.CarRepository
import com.example.ws_work_cars.domain.repository.LeadRepository
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object DataModule {

    /**
     * Função load necessária para enviar para o Application todos os módulos de uma vez
     * */

    fun load() {
        loadKoinModules(networkModule() + repositoryModules() + localModule())
    }

    /**
     * Injeção de dependência quando for solicitado ao Koin um Repository
     * */

    private fun repositoryModules(): Module {

        return module {

            single<CarRepository> { CarRepositoryImpl(service = get()) }

            single<LeadRepository> { LeadRepositoryImpl(service = get(), leadDao = get()) }

        }
    }

    /**
     * Injeção de depedência quando for solicitado ao Koin um Database
     * */

    private fun localModule(): Module {

        return module {

            single { LeadDatabase.getInstance(androidContext()).dao }

        }
    }

    private fun networkModule(): Module {

        return module {

            /**
             * Criação do serviço CarService
             * */

            single<CarService> { createService(factory = get(), client = get()) }

            /**
             * Criação do Okhttp interceptor
             * */

            single {
                val interceptor = HttpLoggingInterceptor {
                    Log.e(OK_HTTP, it)
                }
                interceptor.level = HttpLoggingInterceptor.Level.BODY
                OkHttpClient.Builder()
                    .addInterceptor(interceptor)
                    .build()
            }

            /**
             * Criação da Factory Moshi
             * */

            single {
                Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
            }

        }
    }

    /**
     * Criação de serviços com o Retrofit. Foi utilizado o generics para
     * facilitar a criação de novos serviços casa seja necessário futuramente.
     * */

    private inline fun <reified T> createService(
        factory: Moshi,
        client: OkHttpClient
    ): T {
        return Retrofit.Builder()
            .baseUrl(BASE_URL) // Url base da Api
            .addConverterFactory(MoshiConverterFactory.create(factory)) // Conversor de Json
            .client(client) // Ok Http Interceptor
            .build() // Criação do Retrofit
            .create(T::class.java) // Criação do serviço
    }
}