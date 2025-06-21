package com.example.mojtrening.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mojtrening.data.VjezbaRepository
import com.example.mojtrening.model.VjezbaEntity
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class TreningViewModel(private val repository: VjezbaRepository) : ViewModel() {

    val sveVjezbe: StateFlow<List<VjezbaEntity>> = repository.sveVjezbe
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    fun dodajVjezbu(vjezba: VjezbaEntity) {
        viewModelScope.launch {
            repository.dodajVjezbu(vjezba)
        }
    }

    fun obrisiVjezbu(vjezba: VjezbaEntity) {
        viewModelScope.launch {
            repository.obrisiVjezbu(vjezba)
        }
    }
}
