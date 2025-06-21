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
            .fallbackToDestructiveMigration() // koristi se ako promijeniš strukturu entiteta
            .allowMainThreadQueries() //  koristi se samo za testiranje, ne u produkciji
            .build()

        // Inicijalizacija Repository-ja i ViewModel-a
        val repository = VjezbaRepository(db.vjezbaDao())
        val viewModelFactory = TreningViewModelFactory(repository)

        // UI prikaz
        setContent {
            // ViewModel mora biti kreiran unutar @Composable konteksta
            val factory = TreningViewModelFactory(repository)
            val treningViewModel: TreningViewModel = viewModel(factory = factory)
            val navController = rememberNavController()

            MojTreningTheme {
                CompositionLocalProvider(LocalTreningViewModel provides treningViewModel) {
                    NavigationGraph(navController = navController)
                }
            }
        }
    }
}
