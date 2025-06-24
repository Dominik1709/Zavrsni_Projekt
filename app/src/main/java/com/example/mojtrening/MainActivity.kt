package com.example.mojtrening

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import com.example.mojtrening.data.AppDatabase
import com.example.mojtrening.data.VjezbaRepository
import com.example.mojtrening.data.TreningRepository
import com.example.mojtrening.viewmodel.TreningViewModel
import com.example.mojtrening.viewmodel.TreningViewModelFactory
import com.example.mojtrening.ui.theme.theme.MojTreningTheme



// Globalni ViewModel dostupnost za sve @Composable funkcije
val LocalTreningViewModel = staticCompositionLocalOf<TreningViewModel> {
    error("TreningViewModel nije inicijaliziran.")
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inicijalizacija Room baze
        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "trening_baza"
        )
            .fallbackToDestructiveMigration(true) // koristi se ako promijeniš strukturu entiteta
            .allowMainThreadQueries() //  koristi se samo za testiranje, ne u produkciji
            .build()

        // Inicijalizacija Repository-ja
        val vjezbaRepository = VjezbaRepository(db.vjezbaDao())
        val treningRepository = TreningRepository(db.treningDao())

        // UI prikaz
        setContent {
            // ViewModel mora biti kreiran unutar @Composable konteksta
            val factory = TreningViewModelFactory(vjezbaRepository, treningRepository)
            val treningViewModel: TreningViewModel = viewModel(factory = factory)
            val navController = rememberNavController()

            // Primjenjuje se tema i omogućuje pristup ViewModelu unutar cijelog sučelja
            MojTreningTheme {
                CompositionLocalProvider(LocalTreningViewModel provides treningViewModel) {
                    NavigationGraph(navController = navController)
                }

            }
        }
    }
}
