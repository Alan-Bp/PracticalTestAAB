package com.example.practicaltestaab.data.local.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.practicaltestaab.domain.model.Quote

@Entity(tableName = "bank_table")
data class QuoteEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Int = 0,
    @ColumnInfo(name = "bankName") val bankName: String,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "age") val age: String,
    @ColumnInfo(name = "url") val url: String
)


fun Quote.toDatabase() =
    QuoteEntity(bankName = bankName, description = description, age = age, url = url)
