// LocataireList.kt
package bts.sio.azurimmo.views.locataire

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
import bts.sio.azurimmo.viewsmodel.locataire.LocataireViewModel

@Composable
fun LocataireList(
    viewModel: LocataireViewModel = viewModel(),
    onAddClick: () -> Unit,
    onBackClick: () -> Unit
) {
    val locataires = viewModel.locataires.value
    val isLoading = viewModel.isLoading.value
    val errorMessage = viewModel.errorMessage.value

    LaunchedEffect(Unit) {
        viewModel.getLocataires()
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Column {
            // Header avec bouton retour
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
                    text = "Locataires",
                    style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold)
                )

                IconButton(onClick = onAddClick) {
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
                        items(locataires) { locataire ->
                            LocataireCard(
                                locataire = locataire,
                                onEditClick = { /* TODO: Édition */ },
                                onDeleteClick = { viewModel.deleteLocataire(locataire.id) }
                            )
                        }
                    }
                }
            }
        }
    }
}