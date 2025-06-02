package bts.sio.azurimmo.views.locataire

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
import bts.sio.azurimmo.model.Locataire
import bts.sio.azurimmo.viewsmodel.locataire.LocataireViewModel

@Composable
fun LocataireAdd(
    viewModel: LocataireViewModel = viewModel(),
    onLocataireAdd: () -> Unit,
    onBackClick: () -> Unit
) {
    var nom by remember { mutableStateOf("") }
    var prenom by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var telephone by remember { mutableStateOf("") }

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
                text = "Ajouter un locataire",
                style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold)
            )

            Spacer(modifier = Modifier.width(48.dp)) // Pour équilibrer le layout
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Formulaire
        OutlinedTextField(
            value = nom,
            onValueChange = { nom = it },
            label = { Text("Nom") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = prenom,
            onValueChange = { prenom = it },
            label = { Text("Prénom") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = telephone,
            onValueChange = { telephone = it },
            label = { Text("Téléphone") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = {
                if (nom.isNotBlank() && prenom.isNotBlank()) {
                    val locataire = Locataire(
                        id = 0,
                        nom = nom,
                        prenom = prenom,
                        email = email,
                        telephone = telephone
                    )
                    viewModel.addLocataire(locataire)
                    onLocataireAdd()
                }
            },
            modifier = Modifier.align(Alignment.End),
            enabled = nom.isNotBlank() && prenom.isNotBlank()
        ) {
            Text("Ajouter le locataire")
        }
    }
}