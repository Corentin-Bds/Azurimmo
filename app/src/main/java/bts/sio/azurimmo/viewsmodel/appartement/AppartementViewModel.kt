package bts.sio.azurimmo.viewsmodel.appartement

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.compose.runtime.*
import androidx.lifecycle.viewModelScope
import bts.sio.azurimmo.api.RetrofitInstance
import bts.sio.azurimmo.model.Appartement
import kotlinx.coroutines.launch

class AppartementViewModel : ViewModel() {
    private val _appartements = mutableStateOf<List<Appartement>>(emptyList())
    val appartements: State<List<Appartement>> = _appartements

    private val _isLoading = mutableStateOf(false)
    val isLoading: State<Boolean> = _isLoading

    private val _errorMessage = mutableStateOf<String?>(null)
    val errorMessage: State<String?> = _errorMessage

    fun getAppartements() {
        viewModelScope.launch {
            _isLoading.value = true
            _errorMessage.value = null
            try {
                val response = RetrofitInstance.api.getAppartements()
                _appartements.value = response
            } catch (e: Exception) {
                _errorMessage.value = "Erreur : ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun getAppartementsByBatimentId(batimentId: Int) {
        viewModelScope.launch {
            _isLoading.value = true
            _errorMessage.value = null
            try {
                val response = RetrofitInstance.api.getAppartementsByBatimentId(batimentId)
                _appartements.value = response
            } catch (e: Exception) {
                _errorMessage.value = "Erreur : ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun addAppartement(appartement: Appartement) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val response = RetrofitInstance.api.createAppartement(appartement)
                if (response.isSuccessful) {
                    // Recharger selon le contexte
                    if (_appartements.value.isEmpty() ||
                        _appartements.value.any { it.batiment.id == appartement.batiment.id }) {
                        getAppartementsByBatimentId(appartement.batiment.id)
                    } else {
                        getAppartements()
                    }
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

    fun updateAppartement(id: Int, appartement: Appartement) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val response = RetrofitInstance.api.updateAppartement(id, appartement)
                if (response.isSuccessful) {
                    // Recharger la liste appropriée
                    if (_appartements.value.any { it.batiment.id == appartement.batiment.id }) {
                        getAppartementsByBatimentId(appartement.batiment.id)
                    } else {
                        getAppartements()
                    }
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

    fun deleteAppartement(id: Int) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val response = RetrofitInstance.api.deleteAppartement(id)
                if (response.isSuccessful) {
                    // Recharger la liste actuelle
                    val currentAppartement = _appartements.value.find { it.id == id }
                    if (currentAppartement != null) {
                        getAppartementsByBatimentId(currentAppartement.batiment.id)
                    } else {
                        getAppartements()
                    }
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