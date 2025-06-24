package com.example.mojtrening.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mojtrening.data.VjezbaRepository
import com.example.mojtrening.data.VjezbaEntity
import com.example.mojtrening.data.TreningEntity
import com.example.mojtrening.data.TreningRepository
import kotlinx.coroutines.launch

class TreningViewModel(private val vjezbaRepository: VjezbaRepository,
                       private  val treningRepository: TreningRepository) : ViewModel() {

    val sveVjezbe = vjezbaRepository.sveVjezbe

    fun stvoriTreningISpremiVjezbe(
        nazivTreninga: String?,
        datum: String,
        vjezbe: List<VjezbaEntity>
    ) {
        viewModelScope.launch {
            val trening = TreningEntity(naziv = nazivTreninga, datum = datum)
            val treningId = treningRepository.dodajTrening(trening).toInt()

            val vjezbeSaTreningId = vjezbe.map {
                it.copy(treningId = treningId)
            }

            vjezbeSaTreningId.forEach { vjezbaRepository.dodajVjezbu(it) }
        }
    }
}
