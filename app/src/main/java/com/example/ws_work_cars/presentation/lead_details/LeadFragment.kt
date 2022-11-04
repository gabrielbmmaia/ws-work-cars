package com.example.ws_work_cars.presentation.lead_details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.example.ws_work_cars.R
import com.example.ws_work_cars.databinding.FragmentLeadBinding
import org.koin.androidx.viewmodel.ext.android.viewModel


class LeadFragment : Fragment(R.layout.fragment_lead) {

    private val args: LeadFragmentArgs by navArgs()
    private lateinit var _binding: FragmentLeadBinding
    private val viewModel by viewModel<LeadViewModel>()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLeadBinding.inflate(inflater)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        popularDadoCarros()
        toolbar()
        submitButton()
    }

    private fun submitButton() {

        _binding.acceptButton.setOnClickListener {

            val carId = args.car.id
            val nomeLead = _binding.nomeEditText.text.toString()
            val emailLead = _binding.emailEditText.text.toString()

            viewModel.saveLead(carId, nomeLead, emailLead)


        }
    }

    private fun popularDadoCarros() {
        _binding.modeloCarro.text = args.car.nomeModelo
        _binding.marcaCarro.text = args.car.marcaNome
        _binding.valorCarro.text = args.car.valorFipe
    }

    private fun toolbar() {
        val navController = findNavController()
        val appBarConfig = AppBarConfiguration(navController.graph)
        val toolbar = _binding.toolbar
        toolbar.setupWithNavController(navController, appBarConfig)
    }
}