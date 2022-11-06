package com.example.ws_work_cars.presentation.lead

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ws_work_cars.domain.model.Lead
import com.example.ws_work_cars.domain.useCases.LeadUseCase
import com.example.ws_work_cars.domain.useCases.LeadValidationUseCase
import com.example.ws_work_cars.domain.useCases.SaveLeadUseCase
import com.example.ws_work_cars.domain.useCases.util.ValidationResult
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LeadViewModel(
    private val leadUseCase: LeadUseCase
) : ViewModel() {

    /**
     * Variáveis observáveis
     * */

    private val _emailState = MutableStateFlow<LeadState>(LeadState.Initial)
    val emailState: StateFlow<LeadState> get() = _emailState

    private val _nameState = MutableStateFlow<LeadState>(LeadState.Initial)
    val nameState: StateFlow<LeadState> get() = _nameState

    private val _leadState = MutableStateFlow<LeadState>(LeadState.Initial)
    val leadState: StateFlow<LeadState> get() = _leadState

    /**
     * Função para checar os dados enviados pela View
     * */

    fun saveLead(carId: Long, nomeLead: String, emailLead: String) {
        viewModelScope.launch(IO) {
            leadUseCase.leadValidation(email = emailLead, name = nomeLead).collect { result ->
                when (result) {

                    /**
                     * Controle da caixa de e-mail
                     * */

                    is ValidationResult.EmailError -> {
                        _emailState.value =
                            LeadState.Error(errorMessage = result.errorMessage)
                    }

                    ValidationResult.EmailSuccess -> {
                        _emailState.value =
                            LeadState.Success()
                    }

                    /**
                     * Controle da caixa de nome
                     * */

                    is ValidationResult.NameError -> {
                        _nameState.value =
                            LeadState.Error(errorMessage = result.errorMessage)
                    }

                    ValidationResult.NameSuccess -> {
                        _nameState.value =
                            LeadState.Success()
                    }

                    /**
                     * Controle para em caso de sucesso um Lead ser criado
                     * com o saveLeadUseCase
                     * */

                    is ValidationResult.Success -> {

                        _emailState.value =
                            LeadState.Success()

                        _nameState.value =
                            LeadState.Success()

                        _leadState.value =
                            LeadState.Success(message = result.message)

                        leadUseCase.saveLead(
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
