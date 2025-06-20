package com.example.mojtrening.ui.theme.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.mojtrening.ui.theme.theme.MojTreningTheme

@Composable
fun SettingsScreen(
    onThemeChange: (Boolean) -> Unit, // Funkcija koja omogućava promjenu teme
) {
    var isDarkMode by remember { mutableStateOf(false) } // Početni režim je light
    val selectedUnit by remember { mutableStateOf("kg") } // Postavljen na "kg" kao zadana mjera

    // Layout za ekran postavki
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Naslov za ekran postavki
        Text(
            text = "Postavke",
            style = MaterialTheme.typography.headlineSmall // Zamijenjeno s ispravnim stilom
        )

        Spacer(modifier = Modifier.height(16.dp)) // Razmak između elemenata

        // Prikaz trenutne jedinice mjere
        Text(
            text = "Trenutna jedinica: $selectedUnit",
            style = MaterialTheme.typography.bodyLarge // Zamijenjeno s ispravnim stilom
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Mogućnost odabira teme
        Text(
            text = "Odaberi temu:",
            style = MaterialTheme.typography.bodyLarge // Zamijenjeno s ispravnim stilom
        )

        Row {
            // Prebacivanje između Light i Dark moda
            Button(
                onClick = {
                    isDarkMode = !isDarkMode
                    onThemeChange(isDarkMode)
                },
                modifier = Modifier.padding(top = 8.dp)
            ) {
                Text(text = if (isDarkMode) "Light Tema" else "Dark Tema")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SettingsScreenPreview() {
    var isDarkMode by remember { mutableStateOf(false) }

    MojTreningTheme(darkTheme = isDarkMode) {
        SettingsScreen(onThemeChange = { isDarkMode = it })
    }
}

@Composable
fun SettingsScreen(navController: NavHostController) {
    var isDarkTheme by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Postavke", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(24.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Tamna tema")
            Spacer(modifier = Modifier.width(16.dp))
            Switch(
                checked = isDarkTheme,
                onCheckedChange = { isDarkTheme = it }
            )
        }
    }
}
