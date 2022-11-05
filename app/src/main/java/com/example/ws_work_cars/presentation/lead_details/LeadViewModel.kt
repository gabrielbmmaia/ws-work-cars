package com.example.ws_work_cars.presentation.lead_details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ws_work_cars.domain.use_cases.util.ValidationResult
import com.example.ws_work_cars.domain.model.Lead
import com.example.ws_work_cars.domain.use_cases.LeadValidationUseCase
import com.example.ws_work_cars.domain.use_cases.SaveLeadUseCase
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LeadViewModel(
    private val saveLeadUseCase: SaveLeadUseCase,
    private val leadValidation: LeadValidationUseCase
) : ViewModel() {

    private val _emailState = MutableStateFlow<LeadState>(LeadState.Initial)
    val emailState: StateFlow<LeadState> get() = _emailState

    private val _nameState = MutableStateFlow<LeadState>(LeadState.Initial)
    val nameState: StateFlow<LeadState> get() = _nameState

    private val _leadState = MutableStateFlow<LeadState>(LeadState.Initial)
    val leadState: StateFlow<LeadState> get() = _leadState

    fun saveLead(carId: Long, nomeLead: String, emailLead: String) {
        viewModelScope.launch(IO) {
            leadValidation(email = emailLead, name = nomeLead).collect { result ->
                when (result) {

                    is ValidationResult.EmailError -> {

                        _emailState.value =
                            LeadState.Error(errorMessage = result.errorMessage)

                    }
                    ValidationResult.EmailSuccess -> {

                        _emailState.value =
                            LeadState.Success()

                    }
                    is ValidationResult.NameError -> {

                        _nameState.value =
                            LeadState.Error(errorMessage = result.errorMessage)

                    }
                    ValidationResult.NameSuccess -> {

                        _nameState.value =
                            LeadState.Success()

                    }
                    is ValidationResult.Success -> {

                        _emailState.value =
                            LeadState.Success()

                        _nameState.value =
                            LeadState.Success()

                        _leadState.value =
                            LeadState.Success(message = result.message)

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
        }
    }
}