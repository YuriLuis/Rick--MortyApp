package com.yuri.apprickmorty.data.repositories.remote

import com.yuri.apprickmorty.data.models.PersonagemResponse
import com.yuri.apprickmorty.data.utils.Resource
import retrofit2.Response

interface PersonagemRemoteDataSource {

    suspend fun getPersonagens(pagina: Int): Response<PersonagemResponse>
    suspend fun getPersonagensPorNome(nome: String): Response<PersonagemResponse>

    suspend fun getPersonagensPorStatusEGenero(
        status: String,
        genero: String,
        pagina: Int
    ): Response<PersonagemResponse>

    suspend fun getPersonagensPorGenero(genero: String, pagina: Int): Response<PersonagemResponse>

    suspend fun getPersonagensPorStatus(status: String, pagina: Int): Response<PersonagemResponse>
}