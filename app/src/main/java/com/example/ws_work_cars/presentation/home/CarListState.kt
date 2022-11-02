package com.example.ws_work_cars.presentation.home

import com.example.ws_work_cars.domain.model.Car

sealed class CarListState {

    object Loading : CarListState()
    object Empity : CarListState()
    class Success(val data: List<Car>) : CarListState()
    class Error(val errorMessage: String) : CarListState()

}
