package com.example.mojtrening.ui.theme.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mojtrening.viewmodel.TreningViewModel
import androidx.navigation.NavHostController

@OptIn(ExperimentalMaterial3Api::class)

@Composable
fun PovijestTreningaScreen(
    navController: NavHostController,
    viewModel: TreningViewModel = viewModel()
) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Povijest treninga") })
            Button(
                onClick = { navController.navigate("povijest_screen") },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Pogledaj povijest")
            }

        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            if (viewModel.listaVjezbi.isEmpty()) {
                Text("Još nema spremljenih vježbi.", style = MaterialTheme.typography.bodyLarge)
            } else {
                LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    itemsIndexed(viewModel.listaVjezbi.reversed()) { _, vjezba ->
                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
                        ) {
                            Column(modifier = Modifier.padding(12.dp)) {
                                Text("Naziv: ${vjezba.naziv}")
                                Text("Kilaža: ${vjezba.kilaza} kg")
                                Text("Ponavljanja: ${vjezba.ponavljanja}")
                            }
                        }
                    }
                }
            }
        }
    }
}
