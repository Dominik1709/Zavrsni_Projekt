package com.example.mojtrening.ui.theme.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mojtrening.viewmodel.TreningViewModel
import com.example.mojtrening.model.Vjezba
import androidx.compose.ui.Alignment


@Composable
fun TreningScreen(viewModel: TreningViewModel = viewModel()) {
    var naziv by remember { mutableStateOf("") }
    var kilaza by remember { mutableStateOf("") }
    var ponavljanja by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text("Unesi novu vježbu", style = MaterialTheme.typography.titleMedium)

        OutlinedTextField(
            value = naziv,
            onValueChange = { naziv = it },
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

        Button(
            onClick = {
                if (naziv.isNotBlank() && kilaza.isNotBlank() && ponavljanja.isNotBlank()) {
                    viewModel.dodajVjezbu(Vjezba(naziv, kilaza, ponavljanja))
                    naziv = ""
                    kilaza = ""
                    ponavljanja = ""
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Spremi vježbu")
        }

        HorizontalDivider(thickness = 1.dp)

        Text("Unesene vježbe", style = MaterialTheme.typography.titleMedium)

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxHeight()
        ) {
            itemsIndexed(viewModel.listaVjezbi) { index, vjezba ->
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
                ) {
                    Column(modifier = Modifier.padding(8.dp)) {
                        Text("Naziv: ${vjezba.naziv}")
                        Text("Kilaža: ${vjezba.kilaza} kg")
                        Text("Ponavljanja: ${vjezba.ponavljanja}")

                        Spacer(modifier = Modifier.height(4.dp))

                        Button(
                            onClick = { viewModel.obrisiVjezbu(index) },
                            modifier = Modifier.align(Alignment.End)
                        ) {
                            Text("Obriši")
                        }
                    }
                }
            }
        }

    }
}
