package com.example.mojtrening.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mojtrening.data.VjezbaRepository

class TreningViewModelFactory(private val repository: VjezbaRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TreningViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return TreningViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
