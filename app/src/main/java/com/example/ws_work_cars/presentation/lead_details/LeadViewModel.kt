package com.example.ws_work_cars.presentation.lead_details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ws_work_cars.domain.model.Lead
import com.example.ws_work_cars.domain.use_cases.SaveLeadUseCase
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class LeadViewModel(
    private val saveLeadUseCase: SaveLeadUseCase
) : ViewModel() {


    fun saveLead(carId: Long, nomeLead: String, emailLead: String) {

        viewModelScope.launch(IO) {

            saveLeadUseCase(
                Lead(
                    carId = carId,
                    nomeLead = nomeLead,
                    emailLead = emailLead
                )
            )

        }
    }
}