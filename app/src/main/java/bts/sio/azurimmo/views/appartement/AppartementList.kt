package bts.sio.azurimmo.views.appartement

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import bts.sio.azurimmo.viewsmodel.appartement.AppartementViewModel
import bts.sio.azurimmo.viewsmodel.batiment.BatimentViewModel

@Composable
fun AppartementList(
    viewModelAppart: AppartementViewModel = viewModel(),
    viewModelBat: BatimentViewModel = viewModel(),
    batimentId: Int,
    onAddAppartementClick: () -> Unit,
    onBackClick: () -> Unit
) {
    val appartements = viewModelAppart.appartements.value
    val batiment = viewModelBat.batiment.value
    val isLoadingAppart = viewModelAppart.isLoading.value
    val isLoadingBat = viewModelBat.isLoading.value
    val isLoading = isLoadingAppart || isLoadingBat
    val errorMessageAppart = viewModelAppart.errorMessage.value
    val errorMessageBat = viewModelBat.errorMessage.value
    val errorMessage = errorMessageAppart ?: errorMessageBat

    LaunchedEffect(batimentId) {
        viewModelAppart.getAppartementsByBatimentId(batimentId)
        viewModelBat.getBatiment(batimentId)
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Column {
            // Header avec bouton retour, titre et bouton ajouter
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = onBackClick) {
                    Icon(Icons.Default.ArrowBack, contentDescription = "Retour")
                }

                Text(
                    text = "Appartements",
                    style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold)
                )

                IconButton(onClick = onAddAppartementClick) {
                    Icon(Icons.Default.Add, contentDescription = "Ajouter")
                }
            }

            when {
                isLoading -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }

                errorMessage != null -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = errorMessage,
                            color = MaterialTheme.colorScheme.error,
                            modifier = Modifier.padding(16.dp)
                        )
                    }
                }

                else -> {
                    LazyColumn(
                        modifier = Modifier.padding(horizontal = 16.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        // Informations du bâtiment
                        if (batiment != null) {
                            item {
                                Card(
                                    modifier = Modifier.fillMaxWidth(),
                                    colors = CardDefaults.cardColors(
                                        containerColor = MaterialTheme.colorScheme.primaryContainer
                                    )
                                ) {
                                    Column(
                                        modifier = Modifier.padding(16.dp)
                                    ) {
                                        Text(
                                            text = "Informations du bâtiment",
                                            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                                            color = MaterialTheme.colorScheme.onPrimaryContainer
                                        )
                                        Spacer(modifier = Modifier.height(8.dp))
                                        Text(
                                            text = "Adresse : ${batiment.adresse}",
                                            style = MaterialTheme.typography.bodyMedium,
                                            color = MaterialTheme.colorScheme.onPrimaryContainer
                                        )
                                        Text(
                                            text = "Ville : ${batiment.ville}",
                                            style = MaterialTheme.typography.bodyMedium,
                                            color = MaterialTheme.colorScheme.onPrimaryContainer
                                        )
                                    }
                                }
                            }
                        }

                        // Titre de la liste des appartements
                        item {
                            Text(
                                text = if (appartements.isNotEmpty())
                                    "Liste des appartements (${appartements.size})"
                                else
                                    "Aucun appartement",
                                style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                                modifier = Modifier.padding(vertical = 8.dp)
                            )
                        }

                        // Liste des appartements ou message vide
                        if (appartements.isNotEmpty()) {
                            items(appartements) { appartement ->
                                AppartementCard(appartement = appartement)
                            }
                        } else {
                            item {
                                Card(
                                    modifier = Modifier.fillMaxWidth()
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(32.dp),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Column(
                                            horizontalAlignment = Alignment.CenterHorizontally
                                        ) {
                                            Text(
                                                text = "Aucun appartement dans ce bâtiment",
                                                style = MaterialTheme.typography.bodyLarge,
                                                color = MaterialTheme.colorScheme.onSurfaceVariant
                                            )
                                            Spacer(modifier = Modifier.height(8.dp))
                                            Text(
                                                text = "Cliquez sur + pour ajouter le premier appartement",
                                                style = MaterialTheme.typography.bodyMedium,
                                                color = MaterialTheme.colorScheme.onSurfaceVariant
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}