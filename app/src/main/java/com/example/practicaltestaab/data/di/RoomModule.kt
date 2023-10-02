package com.example.practicaltestaab.data.di

import android.content.Context
import androidx.room.Room
import com.example.practicaltestaab.data.local.db.BankDB
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {
    private const val BANK_DATABASE_NAME = "bank_database"

    @Singleton
    @Provides
    fun providerRoom(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, BankDB::class.java, BANK_DATABASE_NAME).build()

    @Singleton
    @Provides
    fun provideBankDao(db: BankDB) = db.getBankDao()
}
