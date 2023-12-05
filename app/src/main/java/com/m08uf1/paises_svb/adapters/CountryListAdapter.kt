package com.m08uf1.paises_svb.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.m08uf1.paises_svb.R
import com.m08uf1.paises_svb.models.Country

class CountryListAdapter : RecyclerView.Adapter<CountryListAdapter.ViewHolder>() {
    var countries: MutableList<Country> = ArrayList()
    lateinit var context: Context

    fun CountryListAdapter(countryList: MutableList<Country>, context: Context) {
        this.countries = countryList
        this.context = context
    }

    fun updateData(newData: MutableList<Country>) {
        countries = newData
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryListAdapter.ViewHolder{
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(layoutInflater.inflate(R.layout.recycler_custom_row, parent, false))
    }

    override fun onBindViewHolder(holder: CountryListAdapter.ViewHolder, position: Int) {
        val item = countries[position]
        holder.bind(item, context)
    }

    override fun getItemCount(): Int {
        return countries.size
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nameTv = view.findViewById<TextView>(R.id.row_tvName)
        fun bind(item: Country, context: Context) {
            nameTv.text = item.nameEn.toString()
        }

    }
}

