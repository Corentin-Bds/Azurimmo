package bts.sio.azurimmo.views.locataire

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
import bts.sio.azurimmo.model.Locataire

@Composable
fun LocataireCard(
    locataire: Locataire,
    onEditClick: (Locataire) -> Unit,
    onDeleteClick: (Locataire) -> Unit
) {
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
                    Row {
                        Text(
                            text = "Nom : ",
                            style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold)
                        )
                        Text(
                            text = "${locataire.nom} ${locataire.prenom}",
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }

                    if (locataire.email.isNotBlank()) {
                        Row {
                            Text(
                                text = "Email : ",
                                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold)
                            )
                            Text(
                                text = locataire.email,
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }
                    }

                    if (locataire.telephone.isNotBlank()) {
                        Row {
                            Text(
                                text = "Téléphone : ",
                                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold)
                            )
                            Text(
                                text = locataire.telephone,
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }
                    }
                }

                // Boutons d'action
                Row {
                    IconButton(onClick = { onEditClick(locataire) }) {
                        Icon(
                            Icons.Default.Edit,
                            contentDescription = "Modifier",
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                    IconButton(onClick = { onDeleteClick(locataire) }) {
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