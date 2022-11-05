package com.example.ws_work_cars.data.repository

import com.example.ws_work_cars.data.remote.CarService
import com.example.ws_work_cars.domain.model.Car
import com.example.ws_work_cars.domain.repository.CarRepository

class CarRepositoryImpl(
    private val service: CarService
) : CarRepository {

    /**
     * Envio da lista de carros transformado para Car
     * */

    override suspend fun getCarList(): List<Car> {
        val serviceReturn = service.getCarList()
        return serviceReturn.map { carDto ->
            carDto.toCar()
        }
    }
}