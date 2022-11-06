package com.example.ws_work_cars.domain.model

import java.io.Serializable

/**
 * Modelo Car utilizado na camada de View e Viewmodel
 * */

data class Car(
    val ano: String,
    val combustivel: String,
    val cor: String,
    val id: Long,
    val marcaNome: String,
    val nomeModelo: String,
    val numPortas: String,
    val valorFipe: String
) : Serializable
