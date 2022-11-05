package com.example.ws_work_cars.domain.repository

import com.example.ws_work_cars.domain.model.Lead

interface LeadRepository {

    suspend fun saveLead(lead: Lead)

    suspend fun getLeads(): List<Lead>

    suspend fun sendLeadsToApi(leads: List<Lead>): Boolean

    suspend fun clearDataLeads()

}