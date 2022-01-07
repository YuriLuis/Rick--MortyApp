package com.yuri.apprickmorty.data.services.remote.api

import com.yuri.apprickmorty.data.models.Personagem
import com.yuri.apprickmorty.data.models.PersonagemResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RickMortyApi {

    @GET("api/character")
    suspend fun getPersonagens(@Query("page") page: Int): Response<PersonagemResponse>

    @GET("api/character")
    suspend fun getPersonagensPorNome(@Query("name") name: String): Response<PersonagemResponse>

    @GET("api/character")
    suspend fun getPersonagensPorStatusEGenero(
        @Query("status") status: String,
        @Query("gender") gender: String,
        @Query("page")page: Int
    ): Response<PersonagemResponse>

    @GET("api/character")
    suspend fun getPersonagensPorGenero(
        @Query("gender") gender: String,
        @Query("page") page: Int
    ): Response<PersonagemResponse>

    @GET("api/character")
    suspend fun getPersonagensPorStatus(
        @Query("status") status: String,
        @Query("page") page: Int
    ): Response<PersonagemResponse>
}