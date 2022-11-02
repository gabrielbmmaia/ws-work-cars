package com.example.ws_work_cars.data.remote.dto

import com.example.ws_work_cars.domain.model.Car
import com.squareup.moshi.Json

/**
 * Classe que representa a resposta da chamada da API
 * */
data class CarDto(
    val ano: String?,
    val combustivel: String?,
    val cor: String?,
    val id: Long,
    @Json(name = "marca_id")
    val marcaId: Int,
    @Json(name = "marca_nome")
    val marcaNome: String?,
    @Json(name = "nome_modelo")
    val nomeModelo: String?,
    @Json(name = "num_portas")
    val numPortas: String?,
    @Json(name = "timestamp_cadastro")
    val timestampCadastro: String,
    @Json(name = "valor_fipe")
    val valorFipe: String?
) {

    /**
     * Função para transformar o CarDto em um Car
     * */
    fun toCar(): Car {

        return Car(
            ano = ano ?: "",
            combustivel = combustivel ?: "",
            cor = cor ?: "",
            id = id,
            marcaNome = marcaNome ?: "",
            nomeModelo = nomeModelo ?: "",
            numPortas = numPortas ?: "",
            valorFipe = valorFipe ?: ""
        )
    }
}