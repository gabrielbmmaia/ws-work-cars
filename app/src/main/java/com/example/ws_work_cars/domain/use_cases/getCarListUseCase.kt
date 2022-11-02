package com.example.ws_work_cars.domain.use_cases

import com.example.ws_work_cars.core.Resource
import com.example.ws_work_cars.domain.model.Car
import com.example.ws_work_cars.domain.repository.CarRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class getCarListUseCase(
    private val repository: CarRepository
) {
    operator fun invoke(): Flow<Resource<List<Car>>> = flow {
        try {
            emit(Resource.Loading)

            val carList = repository.getCarList()
            emit(Resource.Success(carList))

        } catch (e: Exception) {

            emit(Resource.Error(e.message ?: "Ocorreu um Error"))

        }
    }
}