package com.example.ws_work_cars.domain.model

data class Car(
    val ano: String,
    val combustivel: String,
    val cor: String,
    val id: Int,
    val marcaId: Int,
    val marcaNome: String,
    val nomeModelo: String,
    val numPortas: String,
    val valorFipe: String
)