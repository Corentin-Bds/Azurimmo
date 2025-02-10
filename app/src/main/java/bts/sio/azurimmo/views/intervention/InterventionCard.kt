package bts.sio.azurimmo.views.intervention

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import bts.sio.azurimmo.model.Intervention


@Composable
fun InterventionCard(intervention: Intervention) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 4.dp),
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(text = intervention.id.toString(), style = MaterialTheme.typography.bodyLarge)
            Text(text = intervention.date.toString(), style = MaterialTheme.typography.bodyMedium)
            Text(text = intervention.description, style = MaterialTheme.typography.bodyMedium)
            Text(text = intervention.appartement.batiment.ville, style = MaterialTheme.typography.bodyMedium)
            Text(text = intervention.appartement.batiment.adresse, style = MaterialTheme.typography.bodyMedium)
            Text(text= intervention.intervenant.nom, style = MaterialTheme.typography.bodyMedium)
        }
    }
}