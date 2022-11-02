package com.example.ws_work_cars.data.remote.dto

import com.squareup.moshi.Json

data class CarDto(
    val ano: Int,
    val combustivel: String,
    val cor: String,
    val id: Int,
    @Json(name = "marca_id")
    val marcaId: Int,
    @Json(name = "marca_nome")
    val marcaNome: String,
    @Json(name = "nome_modelo")
    val nomeModelo: String,
    @Json(name = "num_portas")
    val numPortas: Int,
    @Json(name = "timestamp_cadastro")
    val timestampCadastro: Int,
    @Json(name = "valor_fipe")
    val valorFipe: Int
)