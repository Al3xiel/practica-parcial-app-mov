package com.example.practica_parcial.model.client

import com.example.practica_parcial.model.response.WebService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

var BASE_URL = "https://jsonplaceholder.typicode.com/"

object RetrofitClient {
    val webService: WebService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(WebService::class.java)
    }
}