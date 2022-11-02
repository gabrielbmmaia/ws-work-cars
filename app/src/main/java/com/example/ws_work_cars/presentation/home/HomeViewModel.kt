package com.example.ws_work_cars.presentation.home

import androidx.lifecycle.ViewModel
import com.example.ws_work_cars.domain.use_cases.getCarListUseCase

class HomeViewModel(
    private val carList : getCarListUseCase
) : ViewModel() {



}