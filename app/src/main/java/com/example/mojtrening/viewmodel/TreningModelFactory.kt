package com.example.mojtrening.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mojtrening.data.VjezbaRepository
import com.example.mojtrening.data.TreningRepository

class TreningViewModelFactory(private val vjezbaRepository: VjezbaRepository, private  val treningRepository: TreningRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TreningViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return TreningViewModel(vjezbaRepository, treningRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
