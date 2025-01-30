package bts.sio.azurimmo.model

data class Appartement(
    val id: Int,
    val numero: Int,
    val surface: Double,
    val nbrPiece: Int,
    val description: String,

    // Je veux faire une ManyToOne d'Appartement à Batiment
    val batiment: Batiment
)
