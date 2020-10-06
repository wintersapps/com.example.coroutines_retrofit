package com.example.coroutines_retrofit.model

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object CountriesService {

    private const val baseUrl = "https://raw.githubusercontent.com"

    fun getCountriesService(): CountriesApi{
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CountriesApi::class.java)
    }
}