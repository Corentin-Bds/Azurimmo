package bts.sio.azurimmo.model

data class Locataire(
    val id: Int,
    val nom: String,
    val prenom: String,
    val email: String,
    val telephone: String
)