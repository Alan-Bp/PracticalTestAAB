package com.example.practicaltestaab.data.local.db.room

import com.example.practicaltestaab.data.external.web.retrofit.BankService
import com.example.practicaltestaab.data.local.db.entities.QuoteEntity
import com.example.practicaltestaab.domain.model.BankModel
import com.example.practicaltestaab.domain.model.Quote
import com.example.practicaltestaab.domain.model.toDomain
import javax.inject.Inject

class BankRepository @Inject constructor(
    private val api: BankService,
    private val bankDao: BankDao
) {


    suspend fun getAllQuotesFromApi(): List<Quote> {
        val response: List<BankModel> = api.getBankQuo()
        return response.map { it.toDomain() }
    }

    suspend fun getAllQuotesFromDatabase(): List<Quote> {
        val response: List<QuoteEntity> = bankDao.getAllBanks()
        return response.map { it.toDomain() }
    }

    suspend fun insertQuotes(quotes: List<QuoteEntity>) {
        bankDao.insertAll(quotes)
    }

    suspend fun clearQuotes() {
        bankDao.deleteAllQuotes()
    }
}