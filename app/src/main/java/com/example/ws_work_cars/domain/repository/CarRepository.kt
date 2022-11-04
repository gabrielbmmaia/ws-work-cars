package com.example.ws_work_cars.domain.repository

import com.example.ws_work_cars.domain.model.Car
import com.example.ws_work_cars.domain.model.Lead

interface CarRepository {

    suspend fun getCarList(): List<Car>

    suspend fun saveLead(lead: Lead)

    suspend fun sendLeadToApi()

}