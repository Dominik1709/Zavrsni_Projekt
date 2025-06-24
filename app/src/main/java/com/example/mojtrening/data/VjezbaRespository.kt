package com.example.mojtrening.data

import kotlinx.coroutines.flow.Flow

class VjezbaRepository(private val dao: VjezbaDao) {

    val sveVjezbe: Flow<List<VjezbaEntity>> = dao.sveVjezbe()

    suspend fun dodajVjezbu(vjezba: VjezbaEntity) {
        dao.insert(vjezba)
    }

}
