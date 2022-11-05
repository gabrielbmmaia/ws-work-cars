package com.example.ws_work_cars.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.ws_work_cars.R
import com.example.ws_work_cars.databinding.FragmentSplashBinding
import com.example.ws_work_cars.domain.use_cases.SendLeadRoutineUseCase
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * Fragmento inicial do aplicativo
 * */

class SplashFragment(
    private val leadRoutine: SendLeadRoutineUseCase
) : Fragment(R.layout.fragment_splash) {

    private lateinit var _binding: FragmentSplashBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        leadRoutine()

        _binding = FragmentSplashBinding.inflate(inflater)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        toHomeFragment()
    }

    private fun toHomeFragment() {

        lifecycleScope.launch {

            delay(2000)
            findNavController().navigate(R.id.action_splashFragment_to_homeFragment)
        }
    }

    private fun leadRoutine() {
        lifecycleScope.launch(IO) {
            leadRoutine.execute()
        }
    }
}