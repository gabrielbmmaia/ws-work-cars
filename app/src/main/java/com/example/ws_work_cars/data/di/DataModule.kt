package com.example.ws_work_cars.data.di

import android.util.Log
import com.example.ws_work_cars.core.Constants.BASE_URL
import com.example.ws_work_cars.core.Constants.OK_HTTP
import com.example.ws_work_cars.data.remote.CarService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create

object DataModule {

    /**
     * Função load necessária para enviar para o Application todos os módulos de uma vez
     * */
    fun load() {
        loadKoinModules(networkModule())
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
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(factory))
            .client(client)
            .build()
            .create(T::class.java)
    }

}