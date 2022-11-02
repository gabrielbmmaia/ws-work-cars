package com.example.ws_work_cars.data.remote

import com.example.ws_work_cars.data.remote.dto.CarDto
import retrofit2.http.GET

interface CarService {

    @GET("cars.json")
    suspend fun getCarList(): List<CarDto>

}