package com.example.practicaltestaab.data.external.web.retrofit

import com.example.practicaltestaab.data.local.db.entities.QuoteEntity
import com.example.practicaltestaab.domain.model.Quote
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject


class BankService @Inject constructor(private val api: APIService) {

    suspend fun getBankQuo(): List<QuoteEntity> {
        return withContext(Dispatchers.IO) {
            val response = api.getCharacters()
            response.body() ?: emptyList()
        }
    }

}