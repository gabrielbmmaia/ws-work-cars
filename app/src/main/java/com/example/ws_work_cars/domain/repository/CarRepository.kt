package com.example.ws_work_cars.domain.repository

import com.example.ws_work_cars.domain.model.Car

interface CarRepository {

    suspend fun getCarList(): List<Car>

}