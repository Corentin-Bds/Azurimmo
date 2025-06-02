package bts.sio.azurimmo.views.contrat

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import bts.sio.azurimmo.model.Contrat
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun ContratCard(
    contrat: Contrat,
    onEditClick: (Contrat) -> Unit,
    onDeleteClick: (Contrat) -> Unit
) {
    val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())

    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 4.dp),
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    // ID du contrat
                    Row {
                        Text(
                            text = "Contrat n° : ",
                            style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold)
                        )
                        Text(
                            text = contrat.id.toString(),
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }

                    // Montants
                    Row {
                        Text(
                            text = "Loyer : ",
                            style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold)
                        )
                        Text(
                            text = "${String.format("%.2f", contrat.montantLoyer)} €",
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }

                    Row {
                        Text(
                            text = "Charges : ",
                            style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold)
                        )
                        Text(
                            text = "${String.format("%.2f", contrat.montantCharges)} €",
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }

                    // Dates
                    Row {
                        Text(
                            text = "Du : ",
                            style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold)
                        )
                        Text(
                            text = dateFormat.format(contrat.dateDebut),
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }

                    Row {
                        Text(
                            text = "Au : ",
                            style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold)
                        )
                        Text(
                            text = dateFormat.format(contrat.dateFin),
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }

                    // Appartement
                    Row {
                        Text(
                            text = "Appartement : ",
                            style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold)
                        )
                        Text(
                            text = "N°${contrat.appartement.numero} - ${contrat.appartement.batiment.adresse}",
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }

                // Boutons d'action
                Row {
                    IconButton(onClick = { onEditClick(contrat) }) {
                        Icon(
                            Icons.Default.Edit,
                            contentDescription = "Modifier",
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                    IconButton(onClick = { onDeleteClick(contrat) }) {
                        Icon(
                            Icons.Default.Delete,
                            contentDescription = "Supprimer",
                            tint = MaterialTheme.colorScheme.error
                        )
                    }
                }
            }
        }
    }
}