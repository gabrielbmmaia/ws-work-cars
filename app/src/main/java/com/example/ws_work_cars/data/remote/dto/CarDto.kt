package com.example.ws_work_cars.data.remote.dto

import com.example.ws_work_cars.domain.model.Car
import com.squareup.moshi.Json
import java.text.NumberFormat
import java.util.*

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
    val valorFipe: String
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
            valorFipe = tratamentoDoValor(valorFipe)
        )
    }

    /**
     * Essa função serve unicamente para "tratar" os dados inconsistentes que vem da API
     * e formatar o valor para a moeda brasileira.
     *
     * !!! Em outras ocasiões essa função não deverá existir. !!!
     *
     * */

    private fun tratamentoDoValor(valor: String): String {

        val formatador = NumberFormat.getCurrencyInstance(Locale("pt", "br"))
        val valorConvertido = valor.toDouble()

        if (valorConvertido < 1000) {
            return formatador.format(valorConvertido * 1000).toString()
        }

        return formatador.format(valorConvertido).toString()
    }
}