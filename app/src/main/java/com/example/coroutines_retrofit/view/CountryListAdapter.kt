package com.example.coroutines_retrofit.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.coroutines_retrofit.databinding.ItemCountryBinding
import com.example.coroutines_retrofit.model.Country

class CountryListAdapter(private val countries: ArrayList<Country>): RecyclerView.Adapter<CountryListAdapter.CountryViewHolder>() {

    fun updateCountries(newCountries: List<Country>) {
        countries.clear()
        countries.addAll(newCountries)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val inflater: LayoutInflater = LayoutInflater.from(parent.context)
        val view: ItemCountryBinding = ItemCountryBinding.inflate(inflater, parent, false)
        return CountryViewHolder(view)
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        holder.view.country = countries[position]
    }

    override fun getItemCount() = countries.size

    class CountryViewHolder(var view: ItemCountryBinding): RecyclerView.ViewHolder(view.root)
}