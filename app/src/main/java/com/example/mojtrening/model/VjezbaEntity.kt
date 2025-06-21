package com.example.mojtrening.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "vjezbe")
data class VjezbaEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val naziv: String,
    val kilaza: String,
    val ponavljanja: String
)