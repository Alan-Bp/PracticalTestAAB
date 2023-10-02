package com.example.practicaltestaab.data.local.db.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.practicaltestaab.data.local.db.entities.QuoteEntity

@Dao
interface BankDao {

    @Query("SELECT * FROM bank_table ORDER BY bankName DESC")
    suspend fun getAllBanks(): List<QuoteEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(quotes: List<QuoteEntity>)

    @Query("DELETE FROM bank_table")
    suspend fun deleteAllQuotes()

}