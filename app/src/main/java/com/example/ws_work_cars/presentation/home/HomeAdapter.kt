package com.example.ws_work_cars.presentation.home


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

import com.example.ws_work_cars.databinding.HomeRecyclerviewCarBinding
import com.example.ws_work_cars.domain.model.Car

class HomeAdapter(
    private val onItemClicked: (Car) -> Unit = {}
) : RecyclerView.Adapter<HomeAdapter.HomeViewHolder>() {

    private var carList = mutableListOf<Car>()

    inner class HomeViewHolder(private val binding: HomeRecyclerviewCarBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private val modeloCarro = binding.modeloCarro
        private val marcaCarro = binding.marcaCarro
        private val corCarro = binding.corCarro
        private val portasCarro = binding.portasCarro
        private val combustivelCarro = binding.combustivelCarro
        private val anoCarro = binding.anoCarro
        private val valorCarro = binding.valorCarro

        fun bindView(car: Car, onItemClicked: (Car) -> Unit) {
            modeloCarro.text = car.nomeModelo
            marcaCarro.text = car.marcaNome
            corCarro.text = car.cor
            portasCarro.text = car.numPortas
            combustivelCarro.text = car.combustivel
            anoCarro.text = car.ano
            valorCarro.text = car.valorFipe

            itemView.setOnClickListener {
                onItemClicked(car)
            }
        }

    }

    fun setDataList(carList: List<Car>) {
        notifyItemRangeRemoved(0, this.carList.size)
        this.carList.clear()
        this.carList.addAll(carList)
        notifyItemInserted(this.carList.size)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {

        val inflater = LayoutInflater.from(parent.context)
        val binding = HomeRecyclerviewCarBinding.inflate(inflater, parent, false)

        return HomeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        holder.bindView(carList[position], onItemClicked)
    }

    override fun getItemCount(): Int = carList.size
}