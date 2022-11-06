package com.example.ws_work_cars.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ws_work_cars.core.Resource
import com.example.ws_work_cars.domain.useCases.GetCarListUseCase
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeViewModel(
    private val carList: GetCarListUseCase
) : ViewModel() {

    /**
     * Variáveis observáveis
     * */

    private val _carListState = MutableStateFlow<CarListState>(CarListState.Empty)
    val carListState: StateFlow<CarListState> get() = _carListState

    init {
        getCarList()
    }

    /**
     * Função para controlar os tipos de estados da variável observável
     * */

    private fun getCarList() {
        viewModelScope.launch(IO) {
            carList().collect { result ->
                when (result) {

                    is Resource.Error -> {
                        _carListState.value =
                            CarListState.Error(result.messager)
                    }

                    is Resource.Success -> {
                        _carListState.value =
                            CarListState.Success(result.data)
                    }

                    Resource.Loading -> {
                        _carListState.value =
                            CarListState.Loading
                    }
                }
            }
        }
    }
}
