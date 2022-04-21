package com.example.demomvvm.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.demomvvm.data.database.dao.QuoteDao
import com.example.demomvvm.data.database.entities.QuoteEntity

@Database(entities = [QuoteEntity::class], version = 1)
abstract class QuoteDatabase : RoomDatabase() {

    abstract fun getQuoteDao(): QuoteDao

}