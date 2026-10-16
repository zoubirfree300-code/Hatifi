package com.example.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [PhoneEntity::class], version = 1, exportSchema = false)
abstract class HatifiDatabase : RoomDatabase() {

    abstract fun phoneDao(): PhoneDao

    companion object {
        @Volatile
        private var INSTANCE: HatifiDatabase? = null

        fun getInstance(context: Context): HatifiDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    HatifiDatabase::class.java,
                    "hatifi_phone_database"
                ).fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
