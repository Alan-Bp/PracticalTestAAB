package com.example.practicaltestaab.data.external.web.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {
    fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://dev.obtenmas.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}