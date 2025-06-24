package com.example.mojtrening.data

class TreningRepository(private val treningDao: TreningDao) {

    suspend fun dodajTrening(trening: TreningEntity): Long {
        return treningDao.insertTrening(trening)
    }
}
