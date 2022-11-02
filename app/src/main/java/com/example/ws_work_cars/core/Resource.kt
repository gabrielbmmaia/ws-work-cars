package com.example.ws_work_cars.core

sealed class Resource<out T> {

    data class Success<T>(val data: T) : Resource<T>()
    data class Error(val messager: String) : Resource<Nothing>()
    object Loading : Resource<Nothing>()

}
