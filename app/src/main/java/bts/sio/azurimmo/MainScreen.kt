package bts.sio.azurimmo.views

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun MainScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Bienvenue dans Azurimmo",
            style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold),
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(bottom = 32.dp)
        )

        // Menu principal avec boutons
        Column(
            modifier = Modifier.width(250.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            MenuButton(
                text = "Bâtiments",
                onClick = { navController.navigate("batiments") }
            )

            MenuButton(
                text = "Appartements",
                onClick = { navController.navigate("appartements") }
            )

            MenuButton(
                text = "Locataires",
                onClick = { navController.navigate("locataires") }
            )

            MenuButton(
                text = "Contrats",
                onClick = { navController.navigate("contrats") }
            )
        }
    }
}

@Composable
fun MenuButton(
    text: String,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp),
        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary
        )
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onPrimary
        )
    }
}