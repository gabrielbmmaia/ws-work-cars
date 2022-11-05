package com.example.ws_work_cars.presentation.home.adpter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

import com.example.ws_work_cars.databinding.HomeRecyclerviewCarBinding
import com.example.ws_work_cars.domain.model.Car

class HomeAdapter(
    var onItemClicked: (car: Car) -> Unit = {}
) : RecyclerView.Adapter<HomeAdapter.HomeViewHolder>() {

    private var carList = mutableListOf<Car>()

    inner class HomeViewHolder(private val binding: HomeRecyclerviewCarBinding) :
        RecyclerView.ViewHolder(binding.root) {

        /**
         * Objeto para ser enviado quando a view for clicada
         * */

        private lateinit var car: Car

        init {
            itemView.setOnClickListener {
                if (::car.isInitialized) {
                    onItemClicked(car)
                }
            }
        }

        private val modeloCarro = binding.modeloCarro
        private val marcaCarro = binding.marcaCarro
        private val corCarro = binding.corCarro
        private val portasCarro = binding.portasCarro
        private val combustivelCarro = binding.combustivelCarro
        private val anoCarro = binding.anoCarro
        private val valorCarro = binding.valorCarro

        fun bindView(car: Car) {

            this.car = car

            modeloCarro.text = car.nomeModelo
            marcaCarro.text = car.marcaNome
            corCarro.text = car.cor
            portasCarro.text = car.numPortas
            combustivelCarro.text = car.combustivel
            anoCarro.text = car.ano
            valorCarro.text = car.valorFipe
        }
    }

    /**
     * Utilização de um DiffUtil para uma melhor performance da RecyclerView
     * */

    fun setDataList(carList: List<Car>) {
        val diffUtil = AdapterDiffUtil(oldCarList = this.carList, newCarList = carList)
        val diffResults = DiffUtil.calculateDiff(diffUtil)
        this.carList = carList.toMutableList()
        diffResults.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {

        val inflater = LayoutInflater.from(parent.context)
        val binding = HomeRecyclerviewCarBinding.inflate(inflater, parent, false)

        return HomeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        holder.bindView(carList[position])
    }

    override fun getItemCount(): Int = carList.size
}