package com.example.ws_work_cars.data.repository

import com.example.ws_work_cars.data.remote.CarService
import com.example.ws_work_cars.domain.model.Car
import com.example.ws_work_cars.domain.repository.CarRepository

/**
 * Repositorio com todas implementaçãos do CarRepository
 * */

class CarRepositoryImpl(
    private val service: CarService
) : CarRepository {

    /**
     * Função para enviar a lista de carros recebido da Api
     * mapeando-a no modelo Car
     * */

    override suspend fun getCarList(): List<Car> {

        val serviceReturn = service.getCarList()
        return serviceReturn.map { carDto ->
            carDto.toCar()
        }
    }
}
