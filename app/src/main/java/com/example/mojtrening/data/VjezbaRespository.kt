package com.example.mojtrening.data

import com.example.mojtrening.model.VjezbaEntity
import kotlinx.coroutines.flow.Flow

class VjezbaRepository(private val dao: VjezbaDao) {

    val sveVjezbe: Flow<List<VjezbaEntity>> = dao.getAll()

    suspend fun dodajVjezbu(vjezba: VjezbaEntity) {
        dao.insert(vjezba)
    }

    suspend fun obrisiVjezbu(vjezba: VjezbaEntity) {
        dao.delete(vjezba)
    }
}
