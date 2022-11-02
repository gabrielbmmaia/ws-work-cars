package com.example.ws_work_cars.data.remote.dto

import com.example.ws_work_cars.domain.model.Car
import com.squareup.moshi.Json

data class CarDto(
    val ano: String,
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
    val numPortas: String,
    @Json(name = "timestamp_cadastro")
    val timestampCadastro: String,
    @Json(name = "valor_fipe")
    val valorFipe: String
) {

    fun toCar(): Car {

        return Car(
            ano = ano,
            combustivel = combustivel,
            cor = cor,
            id = id,
            marcaId = id,
            marcaNome = marcaNome,
            nomeModelo = nomeModelo,
            numPortas = numPortas,
            valorFipe = valorFipe
        )
    }
}