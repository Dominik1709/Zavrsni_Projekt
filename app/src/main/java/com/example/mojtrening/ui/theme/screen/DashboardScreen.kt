package com.example.mojtrening.ui.theme.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.mojtrening.data.VjezbaEntity
import com.example.mojtrening.viewmodel.TreningViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen(
    navController: NavHostController,
    viewModel: TreningViewModel
) {
    val sveVjezbe by viewModel.sveVjezbe.collectAsState(initial = emptyList())
    val grupiranoPoDatumu: Map<String, List<VjezbaEntity>> = sveVjezbe.groupBy { it.datum }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Moji treninzi",
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary
                )
            )
        },

        floatingActionButton = {
            FloatingActionButton(
                onClick = {navController.navigate("trening_screen") },
                containerColor = MaterialTheme.colorScheme.primary,
                modifier = Modifier.testTag("dodajTreningButton")
            )
            {
                Text("+", color = MaterialTheme.colorScheme.primary)
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
                .fillMaxSize()
        ) {
            Text("Zadnji treninzi:", style = MaterialTheme.typography.headlineSmall, color = MaterialTheme.colorScheme.primary)
            Spacer(modifier = Modifier.height(16.dp))

            if (grupiranoPoDatumu.isEmpty()) {
                Text("Još nema spremljenih treninga.", color = MaterialTheme.colorScheme.onBackground)
            } else {
                LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    grupiranoPoDatumu.forEach { (datum, vjezbeZaDan) ->
                        item {
                            Card(
                                modifier = Modifier.fillMaxWidth(),
                                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
                            ) {
                                Column(modifier = Modifier.padding(12.dp)) {
                                    Text("Trening - $datum", style = MaterialTheme.typography.titleMedium, color = MaterialTheme.colorScheme.onSurface)
                                    Spacer(modifier = Modifier.height(8.dp))
                                    vjezbeZaDan.forEach { vjezba ->
                                        Text("- ${vjezba.naziv}: ${vjezba.ponavljanja} x ${vjezba.kilaza}kg", color = MaterialTheme.colorScheme.onSurface)
                                    }
                                }
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(60.dp)) // za FAB padding
        }
    }
}
