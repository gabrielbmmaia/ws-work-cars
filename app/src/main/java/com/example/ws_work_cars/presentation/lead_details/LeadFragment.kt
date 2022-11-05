package com.example.ws_work_cars.presentation.lead_details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
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
        updateEmail()
        updateName()
        updateLead()
    }

    /**
     * Possíveis mudanças na caixa de texto do EMAIL de acordo
     * com seus possíveis estados
     * */

    private fun updateEmail() {
        lifecycleScope.launchWhenStarted {
            viewModel.emailState.collect { state ->
                when (state) {

                    is LeadState.Error -> {

                        _binding.emailErrorText.text = state.errorMessage
                        _binding.emailErrorText.visibility = View.VISIBLE

                    }
                    is LeadState.Success -> {

                        _binding.emailErrorText.visibility = View.INVISIBLE

                    }
                    else -> {}
                }
            }
        }
    }

    /**
     * Possíveis mudanças na caixa de texto do NOME de acordo
     * com seus possíveis estados
     * */

    private fun updateName() {
        lifecycleScope.launchWhenStarted {
            viewModel.nameState.collect { state ->
                when (state) {

                    is LeadState.Error -> {

                        _binding.nomeErrorText.text = state.errorMessage
                        _binding.nomeErrorText.visibility = View.VISIBLE

                    }
                    is LeadState.Success -> {

                        _binding.nomeErrorText.visibility = View.INVISIBLE

                    }
                    else -> {}
                }
            }
        }
    }

    /**
     * Função para quando o nome e email forem válidos o
     * usuários ser transferido para tela anterior e
     * mostrar um Toast avisando-o que tudo ocorreu bem
     * */

    private fun updateLead() {
        lifecycleScope.launchWhenStarted {
            viewModel.leadState.collect { state ->
                when (state) {

                    is LeadState.Success -> {

                        Toast.makeText(
                            requireContext(),
                            state.message,
                            Toast.LENGTH_SHORT
                        ).show()

                        toHomeFragment()

                    }
                    else -> {}
                }
            }
        }
    }

    /**
     * Função para enviar para a ViewModel os dados necessários
     * */

    private fun submitButton() {

        _binding.acceptButton.setOnClickListener {

            val carId = args.car.id
            val nomeLead = _binding.nomeEditText.text.toString().trim()
            val emailLead = _binding.emailEditText.text.toString().trim()

            viewModel.saveLead(carId, nomeLead, emailLead)

        }
    }

    /**
     * Função para popular os dados do carro enviados pelo argumento
     * da ação do navigation
     * */

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

    private fun toHomeFragment() {
        val action = LeadFragmentDirections.actionLeadFragmentToHomeFragment()
        findNavController().navigate(action)
    }
}