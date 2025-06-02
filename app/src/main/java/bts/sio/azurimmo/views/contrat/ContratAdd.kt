package bts.sio.azurimmo.views.contrat

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import bts.sio.azurimmo.model.Contrat
import bts.sio.azurimmo.model.Appartement
import bts.sio.azurimmo.model.Batiment
import bts.sio.azurimmo.viewsmodel.contrat.ContratViewModel
import java.util.Date

@Composable
fun ContratAdd(
    viewModel: ContratViewModel = viewModel(),
    onContratAdd: () -> Unit,
    onBackClick: () -> Unit
) {
    var montantLoyer by remember { mutableStateOf("") }
    var montantCharges by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Header
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = onBackClick) {
                Icon(Icons.Default.ArrowBack, contentDescription = "Retour")
            }

            Text(
                text = "Ajouter un contrat",
                style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold)
            )

            Spacer(modifier = Modifier.width(48.dp))
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Formulaire simplifié
        OutlinedTextField(
            value = montantLoyer,
            onValueChange = { montantLoyer = it },
            label = { Text("Montant du loyer (€)") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = montantCharges,
            onValueChange = { montantCharges = it },
            label = { Text("Montant des charges (€)") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = {
                if (montantLoyer.isNotBlank() && montantCharges.isNotBlank()) {
                    // Création d'un contrat minimal pour test
                    val appartementTest = Appartement(
                        id = 1,
                        numero = 101,
                        surface = 50.0,
                        nbrPiece = 3,
                        description = "Appartement test",
                        batiment = Batiment(1, "Test", "Test")
                    )

                    val contrat = Contrat(
                        id = 0,
                        montantLoyer = montantLoyer.toDoubleOrNull() ?: 0.0,
                        montantCharges = montantCharges.toDoubleOrNull() ?: 0.0,
                        dateDebut = Date(),
                        dateFin = Date(),
                        appartement = appartementTest
                    )
                    viewModel.addContrat(contrat)
                    onContratAdd()
                }
            },
            modifier = Modifier.align(Alignment.End),
            enabled = montantLoyer.isNotBlank() && montantCharges.isNotBlank()
        ) {
            Text("Ajouter le contrat")
        }
    }
}