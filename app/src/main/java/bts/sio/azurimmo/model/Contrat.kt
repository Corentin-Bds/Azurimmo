package bts.sio.azurimmo.model

import java.util.Date

data class Contrat (
    val id:Int,
    val montantLoyer: Double,
    val montantCharges: Double,
    val dateDebut: Date,
    val dateFin: Date,
    val appartement: Appartement
)