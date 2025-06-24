package com.example.mojtrening.ui.theme.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.mojtrening.data.VjezbaEntity
import com.example.mojtrening.viewmodel.TreningViewModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TreningScreen(
    navController: NavHostController,
    viewModel: TreningViewModel
) {
    // Stanja za unos vježbi
    var nazivVjezbe by remember { mutableStateOf("") }
    var kilaza by remember { mutableStateOf("") }
    var ponavljanja by remember { mutableStateOf("") }

    // Privremena lista vježbi koje će se spremiti kad korisnik potvrdi trening
    var vjezbeLista by remember { mutableStateOf(listOf<VjezbaEntity>()) }

    // Stanje za unos naziva treninga
    var nazivTreninga by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Novi trening",
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.navigate("dashboard_screen") {
                            popUpTo("trening_screen") { inclusive = true }
                        }
                    }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Natrag",
                            tint = MaterialTheme.colorScheme.onPrimary
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // Unos naziva treninga (nije obavezno)
            OutlinedTextField(
                value = nazivTreninga,
                onValueChange = { nazivTreninga = it },
                label = { Text("Naziv treninga (opcionalno)") }
            )

            // Unos jedne vježbe
            OutlinedTextField(
                value = nazivVjezbe,
                onValueChange = { nazivVjezbe = it },
                label = { Text("Naziv vježbe") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = kilaza,
                onValueChange = { kilaza = it },
                label = { Text("Kilaža (kg)") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = ponavljanja,
                onValueChange = { ponavljanja = it },
                label = { Text("Ponavljanja") },
                modifier = Modifier.fillMaxWidth()
            )

            // Gumb za dodavanje vježbe u listu
            Button(
                onClick = {
                    if (nazivVjezbe.isNotBlank() && kilaza.isNotBlank() && ponavljanja.isNotBlank()) {
                        val datum = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())
                        val novaVjezba = VjezbaEntity(
                            naziv = nazivVjezbe,
                            kilaza = kilaza,
                            ponavljanja = ponavljanja,
                            datum = datum,
                            treningId = 0
                        )
                        vjezbeLista = vjezbeLista + novaVjezba

                        // Resetiraj polja
                        nazivVjezbe = ""
                        kilaza = ""
                        ponavljanja = ""
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Dodaj vježbu", color = MaterialTheme.colorScheme.onPrimary)
            }

            // Prikaz trenutno dodanih vježbi
            if (vjezbeLista.isNotEmpty()) {
                Text("Vježbe u treningu:", style = MaterialTheme.typography.titleMedium, color = MaterialTheme.colorScheme.primary)
                LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    itemsIndexed(vjezbeLista) { _, vjezba ->
                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
                        ) {
                            Column(modifier = Modifier.padding(12.dp)) {
                                Text("${vjezba.naziv} - ${vjezba.kilaza}kg x ${vjezba.ponavljanja}", color = MaterialTheme.colorScheme.onSurface)
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Gumb za spremanje treninga i vježbi
            Button(
                onClick = {
                    if (vjezbeLista.isNotEmpty()) {
                        val datum = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())
                        viewModel.stvoriTreningISpremiVjezbe(
                            nazivTreninga = if (nazivTreninga.isBlank()) null else nazivTreninga,
                            datum = datum,
                            vjezbe = vjezbeLista
                        )
                        navController.navigate("dashboard_screen") {
                            popUpTo("trening_screen") { inclusive = true }
                        }
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Spremi trening", color = MaterialTheme.colorScheme.onPrimary)
            }
        }
    }
}
