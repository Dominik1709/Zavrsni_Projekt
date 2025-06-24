package com.example.mojtrening.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface VjezbaDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vjezba: VjezbaEntity)

    @Delete
    suspend fun delete(vjezba: VjezbaEntity)

    @Query("SELECT * FROM vjezba")
    fun sveVjezbe(): Flow<List<VjezbaEntity>>
}