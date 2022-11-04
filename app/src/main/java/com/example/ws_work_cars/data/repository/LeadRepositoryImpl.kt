package com.example.ws_work_cars.data.repository

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
     * Função para enviar Leads para a API
     * */

    override suspend fun sendLeadToApi() {

        val leads = leadDao.getLeads()

        val leadsRequest = leads.map {
            it.toLeadRequest()
        }

        val request = service.sendLead(leadsRequest)

        if (request.isSuccessful) {
            leadDao.clearDb()
        }

    }

}