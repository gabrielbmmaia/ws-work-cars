package com.example.ws_work_cars.presentation.splash

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.ws_work_cars.R
import com.example.ws_work_cars.databinding.FragmentSplashBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * Fragmento inicial do aplicativo
 * */

class SplashFragment : Fragment(R.layout.fragment_splash) {

    private lateinit var _binding: FragmentSplashBinding
    private val viewModel by viewModel<SplashViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        /**
         * Execução da rotina de envio de leads
         * */

        leadRoutine()

        _binding = FragmentSplashBinding.inflate(inflater)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        toHomeFragment()
    }

    /**
     * Navegação para a tela home com um atraso de 2 segundos
     * */

    private fun toHomeFragment() {
        lifecycleScope.launch {

            delay(2000)
            findNavController().navigate(R.id.action_splashFragment_to_homeFragment)
        }
    }

    /**
     * Rotina de envio de Leads quando o viewModel é instancializado
     * */

    private fun leadRoutine() {
        lifecycleScope.launchWhenStarted {
            Log.i("SplashFragment", "leadRoutine: leadRoutine")
            viewModel
        }
    }
}