package com.example.ws_work_cars.presentation.home.adpter

import androidx.recyclerview.widget.DiffUtil
import com.example.ws_work_cars.domain.model.Car


/**
 * Classe auxiliar do adapter
 * */

class AdapterDiffUtil(
    private val oldCarList: List<Car>,
    private val newCarList: List<Car>
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldCarList.size
    }

    override fun getNewListSize(): Int {
        return newCarList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldCarList[oldItemPosition].id == newCarList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return when {
            oldCarList[oldItemPosition].id != newCarList[newItemPosition].id -> {
                return false
            }
            oldCarList[oldItemPosition].marcaNome != newCarList[newItemPosition].marcaNome -> {
                return false
            }
            oldCarList[oldItemPosition].nomeModelo != newCarList[newItemPosition].nomeModelo -> {
                return false
            }
            else -> true
        }
    }
}