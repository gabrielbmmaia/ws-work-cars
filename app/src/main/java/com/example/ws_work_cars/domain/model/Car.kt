package com.example.ws_work_cars.domain.model

/**
 * Classe utilizada fora da camada de Domain
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
)