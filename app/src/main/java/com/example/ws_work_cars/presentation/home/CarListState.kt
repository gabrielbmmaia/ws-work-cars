package com.example.ws_work_cars.presentation.home

import com.example.ws_work_cars.domain.model.Car

/**
 * Classe para controlar os estados da variável do viewModel
 * */

sealed class CarListState {

    object Loading : CarListState()
    object Empty : CarListState()
    class Success(val data: List<Car>) : CarListState()
    class Error(val errorMessage: String) : CarListState()

}
