package bts.sio.azurimmo.viewsmodel.locataire

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import bts.sio.azurimmo.api.RetrofitInstance
import bts.sio.azurimmo.model.Locataire
import kotlinx.coroutines.launch

class LocataireViewModel : ViewModel() {
    private val _locataires = mutableStateOf<List<Locataire>>(emptyList())
    val locataires: State<List<Locataire>> = _locataires

    private val _isLoading = mutableStateOf(false)
    val isLoading: State<Boolean> = _isLoading

    private val _errorMessage = mutableStateOf<String?>(null)
    val errorMessage: State<String?> = _errorMessage

    fun getLocataires() {
        viewModelScope.launch {
            _isLoading.value = true
            _errorMessage.value = null
            try {
                val response = RetrofitInstance.api.getLocataires()
                _locataires.value = response
            } catch (e: Exception) {
                _errorMessage.value = "Erreur : ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun addLocataire(locataire: Locataire) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val response = RetrofitInstance.api.createLocataire(locataire)
                if (response.isSuccessful) {
                    getLocataires() // Recharger la liste
                } else {
                    _errorMessage.value = "Erreur lors de l'ajout : ${response.message()}"
                }
            } catch (e: Exception) {
                _errorMessage.value = "Erreur : ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun updateLocataire(id: Int, locataire: Locataire) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val response = RetrofitInstance.api.updateLocataire(id, locataire)
                if (response.isSuccessful) {
                    getLocataires() // Recharger la liste
                } else {
                    _errorMessage.value = "Erreur lors de la modification : ${response.message()}"
                }
            } catch (e: Exception) {
                _errorMessage.value = "Erreur : ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun deleteLocataire(id: Int) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val response = RetrofitInstance.api.deleteLocataire(id)
                if (response.isSuccessful) {
                    getLocataires() // Recharger la liste
                } else {
                    _errorMessage.value = "Erreur lors de la suppression : ${response.message()}"
                }
            } catch (e: Exception) {
                _errorMessage.value = "Erreur : ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }
}