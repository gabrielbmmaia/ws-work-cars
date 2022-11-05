package com.example.ws_work_cars.data.remote

import com.example.ws_work_cars.data.remote.dto.CarDto
import com.example.ws_work_cars.data.remote.dto.LeadRequest
import com.example.ws_work_cars.domain.model.Lead
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

/**
 * Classe com os serviços da API
 * */
interface CarService {

    @GET("cars.json")
    suspend fun getCarList(): List<CarDto>

    @POST("cars/leads")
    suspend fun sendLead(

        @Body leads: List<Lead>

    ): Response<LeadRequest>

}