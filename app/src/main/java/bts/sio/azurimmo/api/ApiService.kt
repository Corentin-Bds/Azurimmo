package bts.sio.azurimmo.api

import bts.sio.azurimmo.model.Appartement
import bts.sio.azurimmo.model.Batiment
import bts.sio.azurimmo.model.Contrat
import bts.sio.azurimmo.model.Intervention
import bts.sio.azurimmo.model.Locataire
import retrofit2.Response
import retrofit2.http.*

interface ApiService {
    // Bâtiments
    @GET("api/batiments/")
    suspend fun getBatiments(): List<Batiment>

    @GET("api/batiments/id/{batimentId}")
    suspend fun getBatiment(@Path("batimentId") batimentId: Int): Response<Batiment>

    @POST("api/batiments/")
    suspend fun createBatiment(@Body batiment: Batiment): Response<Batiment>

    @PUT("api/batiments/{id}")
    suspend fun updateBatiment(@Path("id") id: Int, @Body batiment: Batiment): Response<Batiment>

    @DELETE("api/batiments/{id}")
    suspend fun deleteBatiment(@Path("id") id: Int): Response<Boolean>

    // Appartements
    @GET("api/appartements")
    suspend fun getAppartements(): List<Appartement>

    @GET("api/appartements/batiment/{batimentId}")
    suspend fun getAppartementsByBatimentId(@Path("batimentId") batimentId: Int): List<Appartement>

    @GET("api/appartements/{id}")
    suspend fun getAppartement(@Path("id") id: Int): Response<Appartement>

    @POST("api/appartements/")
    suspend fun createAppartement(@Body appartement: Appartement): Response<Appartement>

    @PUT("api/appartements/{id}")
    suspend fun updateAppartement(@Path("id") id: Int, @Body appartement: Appartement): Response<Appartement>

    @DELETE("api/appartements/{id}")
    suspend fun deleteAppartement(@Path("id") id: Int): Response<Boolean>

    // Locataires
    @GET("api/locataires/")
    suspend fun getLocataires(): List<Locataire>

    @GET("api/locataires/{id}")
    suspend fun getLocataire(@Path("id") id: Int): Response<Locataire>

    @POST("api/locataires/")
    suspend fun createLocataire(@Body locataire: Locataire): Response<Locataire>

    @PUT("api/locataires/{id}")
    suspend fun updateLocataire(@Path("id") id: Int, @Body locataire: Locataire): Response<Locataire>

    @DELETE("api/locataires/{id}")
    suspend fun deleteLocataire(@Path("id") id: Int): Response<Boolean>

    // Contrats
    @GET("api/contrats/")
    suspend fun getContrats(): List<Contrat>

    @GET("api/contrats/{id}")
    suspend fun getContrat(@Path("id") id: Int): Response<Contrat>

    @GET("api/contrats/appartement/{id}")
    suspend fun getContratByAppartement(@Path("id") appartementId: Int): Response<Contrat>

    @POST("api/contrats/")
    suspend fun createContrat(@Body contrat: Contrat): Response<Contrat>

    @PUT("api/contrats/{id}")
    suspend fun updateContrat(@Path("id") id: Int, @Body contrat: Contrat): Response<Contrat>

    @DELETE("api/contrats/{id}")
    suspend fun deleteContrat(@Path("id") id: Int): Response<Boolean>

    // Interventions
    @GET("api/interventions")
    suspend fun getInterventions(): List<Intervention>

    @GET("api/interventions/{id}")
    suspend fun getIntervention(@Path("id") id: Int): Response<Intervention>

    @PUT("api/interventions/{id}")
    suspend fun updateIntervention(@Path("id") id: Int, @Body intervention: Intervention): Response<Intervention>
}