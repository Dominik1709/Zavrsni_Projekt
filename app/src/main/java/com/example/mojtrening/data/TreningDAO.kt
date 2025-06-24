package com.example.mojtrening.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface TreningDao {

    @Insert
    suspend fun insertTrening(trening: TreningEntity): Long

    @Query("SELECT * FROM trening ORDER BY datum DESC")
    fun getSviTreninzi(): Flow<List<TreningEntity>>

    @Query("SELECT * FROM trening WHERE id = :id LIMIT 1")
    suspend fun getTreningById(id: Int): TreningEntity?
}
