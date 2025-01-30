package bts.sio.azurimmo.viewsmodel.appartement

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import bts.sio.azurimmo.model.Batiment
import androidx.compose.runtime.*
import androidx.lifecycle.viewModelScope
import bts.sio.azurimmo.model.Appartement
import kotlinx.coroutines.launch

// ViewModel pour gérer les données des bâtiments
class AppartementViewModel : ViewModel() {
    // Liste mutable des bâtiments
    private val _appartements = mutableStateOf(emptyList<Appartement>())
    val appartements: State<List<Appartement>> = _appartements
    init {
// Simuler un chargement de données initiales
        getAppartements()
    }
    // Fonction pour simuler le chargement de bâtiments
    private fun getAppartements() {
        viewModelScope.launch {
            _appartements.value = listOf(
                Appartement(1, 101, 80.0, 2, "Appartement moderne", Batiment(1, "123 Rue Principale", "Nice")),
                Appartement(2, 202, 120.0, 3, "Appartement spacieux", Batiment(2, "456 Avenue des Champs", "Marseille")),
                Appartement(3, 303, 90.0, 2, "Appartement moderne", Batiment(3, "789 Boulevard Haussmann", "Marseille"))
            )
        }
    }
}