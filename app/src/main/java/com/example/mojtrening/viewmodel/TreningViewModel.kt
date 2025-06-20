package com.example.mojtrening.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.mojtrening.model.Vjezba


class TreningViewModel : ViewModel() {
    // Lista svih vježbi unesenih kroz formu
    var listaVjezbi = mutableStateListOf<Vjezba>()
        private set

    fun dodajVjezbu(vjezba: Vjezba) {
        listaVjezbi.add(vjezba)
    }

    fun obrisiVjezbu(index: Int) {
        if (index >= 0 && index < listaVjezbi.size) {
            listaVjezbi.removeAt(index)
        }
    }

}