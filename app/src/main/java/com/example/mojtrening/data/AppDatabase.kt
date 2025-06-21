package com.example.mojtrening.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.mojtrening.model.VjezbaEntity

@Database(
    entities = [VjezbaEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun vjezbaDao(): VjezbaDao
}