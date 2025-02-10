package bts.sio.azurimmo.model

import java.util.Date

data class Intervention (
    val id: Int,
    val date: Date,
    val description: String,
    val appartement: Appartement,
    val intervenant: Intervenant
)
