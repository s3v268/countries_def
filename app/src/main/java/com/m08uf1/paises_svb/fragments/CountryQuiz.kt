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
import kotlin.random.Random

class CountryQuiz : Fragment() {
    lateinit var binding: FragmentCountryQuizBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCountryQuizBinding.inflate(inflater, container, false)

        //rebem dades la primera vegada (en la creació del fragment)
        val countryListFragment = requireActivity().supportFragmentManager.findFragmentByTag("f0") as? CountryList
        countryListFragment?.sendGameData()

        return binding.root
        //IMPORTANTE ESTO VA AL FINAL
        return binding.root

    }

    fun updateData(data: MutableList<Country>) {
        val randomNumber = Random.nextInt(1, 101)
        //canviem ui amb les dades noves
        binding.tvPoints.text = data.get(randomNumber).nameEn.toString()
    }

}