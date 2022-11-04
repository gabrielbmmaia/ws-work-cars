package com.example.ws_work_cars.data.repository

import android.util.Log
import com.example.ws_work_cars.data.local.dao.LeadDao
import com.example.ws_work_cars.data.local.entities.LeadDb
import com.example.ws_work_cars.data.remote.CarService
import com.example.ws_work_cars.domain.model.Lead
import com.example.ws_work_cars.domain.repository.LeadRepository

class LeadRepositoryImpl(
    private val service: CarService,
    private val leadDao: LeadDao
) : LeadRepository {

    /**
     * Função para salvar leads no banco de dados
     * */

    override suspend fun saveLead(lead: Lead) {
        leadDao.saveLead(
            LeadDb(
                carId = lead.carId,
                nomeLead = lead.nomeLead,
                emailLead = lead.emailLead
            )
        )
    }

    /**
     * Função para enviar Leads para a API. Primeiro é verificado
     * se a lista de leads do banco de dados não está vazia. Caso
     * não esteja, é enviado a lista com os leads para a Api e em
     * caso de sucesso o banco de dados local é limpo.
     * */

    override suspend fun sendLeadToApi() {

        val leads = leadDao.getLeads()

        val leadsRequest = leads.map {
            it.toLeadRequest()
        }

        if (leadsRequest.isNotEmpty()) {

            try {

                val request = service.sendLead(leadsRequest)

                if (request.isSuccessful) {
                    leadDao.clearDb()
                }

            } catch (e: Exception) {
                Log.e("SEND_LEAD", "sendLeadToApi: ", e)
            }
        }
    }
}