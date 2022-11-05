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

    override suspend fun getLeads(): List<Lead> {

        val leads = leadDao.getLeads().map { leadDb ->
            leadDb.toLead()
        }

        return leads

    }

    override suspend fun sendLeadsToApi(leads: List<Lead>): Boolean {

        val request = service.sendLead(leads)

        return request.isSuccessful

    }

    override suspend fun clearDataLeads() {

        leadDao.clearDb()

    }

}