package com.example.practicaltestaab.domain.useCases.room

import com.example.practicaltestaab.data.local.db.entities.toDatabase
import com.example.practicaltestaab.data.local.db.room.BankRepository
import com.example.practicaltestaab.domain.model.Quote
import javax.inject.Inject

class GetBankUseCase @Inject constructor(private val repository: BankRepository) {
    suspend operator fun invoke():List<Quote>{
        val quotes = repository.getAllQuotesFromApi()
//
        return if(quotes.isNotEmpty()){
            repository.clearQuotes()
            repository.insertQuotes(quotes.map { it.toDatabase() })
            quotes
        }else{
            repository.getAllQuotesFromDatabase()
        }
    }
}