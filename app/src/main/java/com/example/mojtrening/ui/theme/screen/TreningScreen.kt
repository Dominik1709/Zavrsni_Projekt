package com.example.mojtrening.ui.theme.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.mojtrening.ui.theme.theme.MojTreningTheme
import com.example.mojtrening.viewmodel.TreningViewModel


@Composable
fun TreningScreen() {
    // State za pohranu unesenih podataka
    var exerciseName by remember { mutableStateOf("") }
    var repetitions by remember { mutableStateOf("") }
    var weight by remember { mutableStateOf("") }
    var isSaved by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Naslov ekrana
        Text(
            text = "Unos Treninga",
            style = MaterialTheme.typography.headlineSmall
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Polje za unos naziva vježbe
        OutlinedTextField(
            value = exerciseName,
            onValueChange = { exerciseName = it },
            label = { Text("Naziv vježbe") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Polje za unos broja ponavljanja
        OutlinedTextField(
            value = repetitions,
            onValueChange = { repetitions = it },
            label = { Text("Ponavljanja") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Polje za unos kilaže
        OutlinedTextField(
            value = weight,
            onValueChange = { weight = it },
            label = { Text("Kilaža") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Gumb za spremanje treninga
        Button(
            onClick = {
                // Spremanje podataka, možeš implementirati logiku spremanja
                isSaved = true
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Spremi Trening")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Poruka koja se prikazuje kad je trening spremljen
        if (isSaved) {
            Text(text = "Trening spremljen!", style = MaterialTheme.typography.bodyLarge)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TreningScreenPreview() {
    MojTreningTheme {
        TreningScreen()
    }
}


@Composable
fun TreningScreen(navController: NavHostController) {
    var nazivVjezbe by remember { mutableStateOf("") }
    var brojSerija by remember { mutableStateOf("") }
    var brojPonavljanja by remember { mutableStateOf("") }
    var kilaza by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Top
    ) {
        Text(text = "Unos treninga", style = MaterialTheme.typography.headlineSmall)

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = nazivVjezbe,
            onValueChange = { nazivVjezbe = it },
            label = { Text("Naziv vježbe") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = brojSerija,
            onValueChange = { brojSerija = it },
            label = { Text("Broj serija") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = brojPonavljanja,
            onValueChange = { brojPonavljanja = it },
            label = { Text("Broj ponavljanja") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = kilaza,
            onValueChange = { kilaza = it },
            label = { Text("Kilaža (kg)") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                // Ovdje možeš dodati logiku za spremanje unosa ili resetiranje polja
                nazivVjezbe = ""
                brojSerija = ""
                brojPonavljanja = ""
                kilaza = ""
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Spremi")
        }
    }
}

@Composable
fun TreningScreen(navController: NavHostController, viewModel: TreningViewModel = viewModel()) {
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
                    viewModel.dodajVjezbu(naziv, kilaza, ponavljanja)
                    naziv = ""
                    kilaza = ""
                    ponavljanja = ""
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Spremi vježbu")
        }

        HorizontalDivider()

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
                    }
                }
            }
        }
    }
}