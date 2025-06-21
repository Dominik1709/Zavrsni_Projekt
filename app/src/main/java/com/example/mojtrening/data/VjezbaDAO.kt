package com.example.mojtrening.data

import androidx.room.*
import com.example.mojtrening.model.VjezbaEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface VjezbaDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vjezba: VjezbaEntity)

    @Delete
    suspend fun delete(vjezba: VjezbaEntity)

    @Query("SELECT * FROM vjezbe ORDER BY id DESC")
    fun getAll(): Flow<List<VjezbaEntity>>
}