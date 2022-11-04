package com.example.ws_work_cars.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.ws_work_cars.R
import com.example.ws_work_cars.databinding.FragmentHomeBinding
import com.example.ws_work_cars.presentation.home.adpter.HomeAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.math.BigDecimal


class HomeFragment : Fragment(R.layout.fragment_home) {

    private lateinit var _binding: FragmentHomeBinding
    private lateinit var adapter: HomeAdapter
    private val viewModel by viewModel<HomeViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater)
        initRecyclerView()
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        updateUi()
        toLeadFragment()
    }



    private fun updateUi() {
        lifecycleScope.launchWhenStarted {

            viewModel.carListState.collect { state ->

                when (state) {

                    is CarListState.Error -> {
                        _binding.linearLayout.isVisible = false
                        Toast.makeText(
                            requireContext(),
                            state.errorMessage,
                            Toast.LENGTH_SHORT
                        )
                            .show()
                    }

                    is CarListState.Success -> {
                        _binding.linearLayout.isVisible = false
                        _binding.homeRecyclerview.isVisible = true
                        adapter.setDataList(state.data)

                    }

                    CarListState.Loading -> {
                        _binding.homeRecyclerview.isVisible = false
                        _binding.linearLayout.isVisible = true
                    }

                    else -> {}
                }

            }

        }
    }

    private fun initRecyclerView() {
        adapter = HomeAdapter()
        _binding.homeRecyclerview.adapter = this.adapter
    }

    private fun toLeadFragment() {
        adapter.onItemClicked = { car ->
            val action = HomeFragmentDirections.actionHomeFragmentToLeadFragment(car)
            findNavController().navigate(action)
        }
    }
}