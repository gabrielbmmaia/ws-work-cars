package com.example.ws_work_cars.core


/**
 * Classe para facilitar o tratamento de erro das chamadas de API.
 * */

sealed class Resource<out T> {

    data class Success<T>(val data: T) : Resource<T>()
    data class Error(val messager: String) : Resource<Nothing>()
    object Loading : Resource<Nothing>()

}
