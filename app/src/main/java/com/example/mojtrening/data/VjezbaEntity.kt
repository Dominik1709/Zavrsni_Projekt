package com.example.mojtrening.data

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "vjezba",
    foreignKeys = [
        ForeignKey(
            entity = TreningEntity::class,
            parentColumns = ["id"],
            childColumns = ["treningId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class VjezbaEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val naziv: String,
    val kilaza: String,
    val ponavljanja: String,
    val datum: String,
    val treningId: Int // poveznica s TreningEntity
)