package com.example.ws_work_cars.presentation.lead

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.example.ws_work_cars.R
import com.example.ws_work_cars.core.extensions.invisible
import com.example.ws_work_cars.core.extensions.toast
import com.example.ws_work_cars.core.extensions.visible
import com.example.ws_work_cars.databinding.FragmentLeadBinding
import org.koin.androidx.viewmodel.ext.android.viewModel


class LeadFragment : Fragment(R.layout.fragment_lead) {

    private var _binding: FragmentLeadBinding? = null
    private val binding get() = _binding!!

    private val args: LeadFragmentArgs by navArgs()
    private val viewModel by viewModel<LeadViewModel>()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLeadBinding.inflate(inflater, container, false)
        return binding.root
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
                        binding.emailErrorText.text = state.errorMessage
                        binding.emailErrorText.visible()
                    }

                    is LeadState.Success -> {
                        binding.emailErrorText.invisible()
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
                        binding.nomeErrorText.text = state.errorMessage
                        binding.nomeErrorText.visible()
                    }

                    is LeadState.Success -> {
                        binding.nomeErrorText.invisible()
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
                        requireContext().toast(state.message ?: "")
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
        binding.acceptButton.setOnClickListener {
            val carId = args.car.id
            val nomeLead = binding.nomeEditText.text.toString().trim()
            val emailLead = binding.emailEditText.text.toString().trim()
            viewModel.saveLead(carId, nomeLead, emailLead)
        }
    }

    /**
     * Função para popular os dados do carro enviados pelo argumento
     * da ação do navigation
     * */

    private fun popularDadoCarros() {
        with(binding) {
            modeloCarro.text = args.car.nomeModelo
            marcaCarro.text = args.car.marcaNome
            valorCarro.text = args.car.valorFipe
        }
    }

    private fun toolbar() {
        val navController = findNavController()
        val appBarConfig = AppBarConfiguration(navController.graph)
        val toolbar = binding.toolbar
        toolbar.setupWithNavController(navController, appBarConfig)
    }

    private fun toHomeFragment() {
        val action = LeadFragmentDirections.actionLeadFragmentToHomeFragment()
        findNavController().navigate(action)
    }
}
