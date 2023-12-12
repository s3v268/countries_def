package com.m08uf1.paises_svb.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.m08uf1.paises_svb.R
import com.m08uf1.paises_svb.databinding.FragmentCountryQuizBinding
import com.m08uf1.paises_svb.interfaces.CommunicatorInterface
import com.m08uf1.paises_svb.models.Country

class CountryQuiz : Fragment(), CommunicatorInterface {
    lateinit var binding: FragmentCountryQuizBinding
    lateinit var communicatorInterface: CommunicatorInterface

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCountryQuizBinding.inflate(inflater, container, false)

        //IMPORTANT POSAR-HO
        communicatorInterface = activity as CommunicatorInterface
        //IMPORTANTE ESTO VA AL FINAL
        return binding.root

    }

    override fun goToFragment(fragmentNum: Int) {
        TODO("Not yet implemented")
    }

    override fun sendNotificationMockup(tab_position: Int) {
        TODO("Not yet implemented")
    }

    override fun onDataReceived(dat: MutableList<Country>) {
        TODO("Not yet implemented")
    }
}