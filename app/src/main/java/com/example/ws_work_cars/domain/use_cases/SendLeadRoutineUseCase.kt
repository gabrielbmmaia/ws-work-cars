package com.example.ws_work_cars.domain.use_cases

import android.util.Log
import com.example.ws_work_cars.domain.repository.LeadRepository

/**
 * Função para enviar Leads para a API.
 * Primeiro é verificado se a lista de leads do
 * banco de dados não está vazia. Caso não esteja,
 * é enviado a lista com os leads para a Api e em
 * caso de sucesso o banco de dados local é limpo.
 * Com isso, é evitado envio de listas vazias para Api.
 * */

class SendLeadRoutineUseCase(
    private val repository: LeadRepository
) {

    suspend fun execute() {

        val leadsList = repository.getLeads()

        if (leadsList.isNotEmpty()) {

            try {

                val request = repository.sendLeadsToApi(leadsList)

                if (request) {

                    repository.clearDataLeads()

                }

            } catch (e: Exception) {

                Log.e("LEAD_ROUTINE", e.toString() )

            }

        }

    }

}