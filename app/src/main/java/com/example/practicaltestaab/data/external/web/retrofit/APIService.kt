package com.example.practicaltestaab.data.external.web.retrofit

import com.example.practicaltestaab.data.local.db.entities.QuoteEntity
import com.example.practicaltestaab.domain.model.BankModel
import com.example.practicaltestaab.domain.model.Quote
import retrofit2.Response
import retrofit2.http.GET

interface APIService {
    @GET("catom/api/challenge/banks/")
    suspend fun getCharacters(): Response<List<BankModel>>

}