package com.example.mojtrening.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "trening")
data class TreningEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val naziv: String?, // Može biti null ako korisnik ne unosi ime
    val datum: String   // Format: yyyy-MM-dd
)
