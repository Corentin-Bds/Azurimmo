package bts.sio.azurimmo.views.appartement

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import bts.sio.azurimmo.model.Appartement

@Composable
fun AppartementCardGlobal(appartement: Appartement) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 4.dp),
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            // Numéro d'appartement et bâtiment
            Row {
                Text(
                    text = "Appartement n° : ",
                    style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold)
                )
                Text(
                    text = appartement.numero.toString(),
                    style = MaterialTheme.typography.bodyLarge
                )
            }

            // Adresse du bâtiment
            Row {
                Text(
                    text = "Adresse : ",
                    style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold)
                )
                Text(
                    text = "${appartement.batiment.adresse}, ${appartement.batiment.ville}",
                    style = MaterialTheme.typography.bodyMedium
                )
            }

            // Surface et pièces
            Row {
                Text(
                    text = "Surface : ",
                    style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold)
                )
                Text(
                    text = "${String.format("%.2f", appartement.surface)} m²",
                    style = MaterialTheme.typography.bodyMedium
                )

                Spacer(modifier = Modifier.width(16.dp))

                Text(
                    text = "Pièces : ",
                    style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold)
                )
                Text(
                    text = appartement.nbrPiece.toString(),
                    style = MaterialTheme.typography.bodyMedium
                )
            }

            // Description
            if (appartement.description.isNotBlank()) {
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Description : ",
                    style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold)
                )
                Text(
                    text = appartement.description,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(top = 4.dp)
                )
            }
        }
    }
}