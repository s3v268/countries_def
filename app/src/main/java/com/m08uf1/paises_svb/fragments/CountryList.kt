package com.m08uf1.paises_svb.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.m08uf1.paises_svb.adapters.CountryListAdapter
import com.m08uf1.paises_svb.databinding.FragmentCountryListBinding
import com.m08uf1.paises_svb.interfaces.CommunicatorInterface
import com.m08uf1.paises_svb.models.Countries
import com.m08uf1.paises_svb.models.Country


class CountryList : Fragment() {
    lateinit var binding: FragmentCountryListBinding
    lateinit var communicatorInterface: CommunicatorInterface
    lateinit var countryRecycler : RecyclerView
    val countryAdapter : CountryListAdapter = CountryListAdapter()
    lateinit var countryList: MutableList<Country>

    private var gson: Gson = Gson()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCountryListBinding.inflate(layoutInflater)
        //IMPORTANT POSAR-HO
        communicatorInterface = activity as CommunicatorInterface

        countryList = parseJSON()
        setUpRecyclerView(countryList)
        setUpSearchBar(countryList)

        /*
        binding.btnFragNext1.setOnClickListener {
            Toast.makeText(context, "lasla", Toast.LENGTH_SHORT).show()
            communicatorInterface.goToFragment(1)
        }
        binding.btnFragPrev1.setOnClickListener {
            Toast.makeText(context, "lasla", Toast.LENGTH_SHORT).show()
            communicatorInterface.goToFragment(2)
        }
        */

        //IMPORTANTE ESTO VA AL FINAL
        return binding.root

    }

    private fun parseJSON(): MutableList<Country> {
        var json: String = context?.assets?.open("countries.json")?.bufferedReader().use { it?.readText()
            ?: "Error loading json" }
        var countries = gson.fromJson(json, Countries::class.java)
        return countries.countries
    }

    private fun setUpSearchBar(countryList: MutableList<Country>) {
        binding.searchbarEd.addTextChangedListener { input ->
            val filteredList: MutableList<Country> = countryList.filter { country ->
                country.nameEn.lowercase().contains(input.toString().lowercase())
            }.toMutableList()
            countryAdapter.updateData(filteredList)
        }
    }


    private fun setUpRecyclerView(countryList: MutableList<Country>) {
        countryRecycler = binding.countryReciclerView as RecyclerView
        countryRecycler.setHasFixedSize(true)
        countryRecycler.layoutManager = LinearLayoutManager(context)

        // llamamos al 'constructor' para pasarle los datos
        countryAdapter.CountryListAdapter(countryList, requireContext())

        countryRecycler.adapter = countryAdapter
    }

}