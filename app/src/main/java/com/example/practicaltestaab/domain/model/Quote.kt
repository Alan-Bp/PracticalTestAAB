package com.example.practicaltestaab.domain.model

import com.example.practicaltestaab.data.local.db.entities.QuoteEntity

data class Quote(val bankName: String, val description: String, val age: String, val url: String)

fun BankModel.toDomain() = Quote(bankName, description, age, url)
fun QuoteEntity.toDomain() = Quote(bankName, description, age, url)
