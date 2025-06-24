package com.example.mojtrening.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [VjezbaEntity::class, TreningEntity::class],
    version = 2,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun vjezbaDao(): VjezbaDao
    abstract fun treningDao(): TreningDao
}