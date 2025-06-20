package com.example.mojtrening.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.mojtrening.model.Vjezba


class TreningViewModel : ViewModel() {
    // Lista svih vježbi unesenih kroz formu
    var listaVjezbi = mutableStateListOf<Vjezba>()
        private set

    fun dodajVjezbu(naziv: String, kilaza: String, ponavljanja: String) {
        listaVjezbi.add(Vjezba(naziv, kilaza, ponavljanja))
    }

}