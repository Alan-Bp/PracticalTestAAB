package com.example.practicaltestaab.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.practicaltestaab.data.local.db.entities.QuoteEntity
import com.example.practicaltestaab.data.local.db.room.BankDao

@Database(entities = [QuoteEntity::class], version = 1)
abstract class BankDB : RoomDatabase() {
    abstract fun getBankDao(): BankDao
}