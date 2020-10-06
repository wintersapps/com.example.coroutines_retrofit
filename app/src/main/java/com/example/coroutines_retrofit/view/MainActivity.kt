package com.example.coroutines_retrofit.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.coroutines_retrofit.databinding.ActivityMainBinding
import com.example.coroutines_retrofit.viewmodel.ListViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: ListViewModel
    private val countriesAdapter = CountryListAdapter(arrayListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProviders.of(this).get(ListViewModel::class.java)

        viewModel.refresh()

        binding.countriesList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = countriesAdapter
        }

        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.countries.observe(this, { countries ->
            countries?.let {
                binding.countriesList.visibility = View.VISIBLE
                countriesAdapter.updateCountries(it)
            }
        })

        viewModel.countryLoadError.observe(this, { isError ->
            binding.errorTextView.visibility = if(isError == "") View.GONE else View.VISIBLE
        })

        viewModel.loading.observe(this, { isLoading ->
            isLoading?.let {
                binding.progressBar.visibility = if(it) View.VISIBLE else View.GONE
                if(it) {
                    binding.errorTextView.visibility = View.GONE
                    binding.countriesList.visibility = View.GONE
                }
            }
        })
    }
}