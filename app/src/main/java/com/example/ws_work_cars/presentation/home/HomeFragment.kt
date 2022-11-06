package com.example.ws_work_cars.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.ws_work_cars.R
import com.example.ws_work_cars.core.extensions.disabled
import com.example.ws_work_cars.core.extensions.enabled
import com.example.ws_work_cars.core.extensions.toast
import com.example.ws_work_cars.databinding.FragmentHomeBinding
import com.example.ws_work_cars.presentation.home.adapter.HomeAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel


class HomeFragment : Fragment(R.layout.fragment_home) {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: HomeAdapter
    private val viewModel by viewModel<HomeViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        updateUi()
        toLeadFragment()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    /**
     * A tela de início é atualizada de acordo com os diferentes estados
     * */

    private fun updateUi() {
        lifecycleScope.launchWhenStarted {
            viewModel.carListState.collect { state ->
                when (state) {

                    is CarListState.Error -> {
                        binding.progressBar.disabled()
                        requireContext().toast(state.errorMessage)
                    }

                    is CarListState.Success -> {
                        binding.progressBar.disabled()
                        binding.homeRecyclerview.enabled()
                        adapter.setDataList(state.data)
                    }

                    CarListState.Loading -> {
                        binding.homeRecyclerview.disabled()
                        binding.progressBar.enabled()
                    }

                    else -> {}
                }
            }
        }
    }

    private fun initRecyclerView() {
        adapter = HomeAdapter()
        binding.homeRecyclerview.adapter = this.adapter
    }

    /**
     * Está sendo enviado como argumento o carro clicado
     * */

    private fun toLeadFragment() {
        adapter.onItemClicked = { car ->
            val action = HomeFragmentDirections.actionHomeFragmentToLeadFragment(car)
            findNavController().navigate(action)
        }
    }
}
