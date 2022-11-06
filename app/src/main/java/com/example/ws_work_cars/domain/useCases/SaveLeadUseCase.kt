package com.example.ws_work_cars.domain.useCases

import com.example.ws_work_cars.domain.model.Lead
import com.example.ws_work_cars.domain.repository.LeadRepository

class SaveLeadUseCase(
    private val repository: LeadRepository
) {

    suspend operator fun invoke(lead: Lead){
        repository.saveLead(lead)
    }
}
