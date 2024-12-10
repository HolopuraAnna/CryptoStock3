package com.example.cryptostock3.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.cryptostock3.data.api.ApiResponse

@Database(entities = [ApiResponse::class], version = 1)
public abstract class StockDatabase : RoomDatabase() {

    abstract fun wordDao(): DaoStockItem

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: StockDatabase? = null

        fun getDatabase(context: Context): StockDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                INSTANCE?.let { return it }
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    StockDatabase::class.java,
                    "stock_items_database"
                )
                    .build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}