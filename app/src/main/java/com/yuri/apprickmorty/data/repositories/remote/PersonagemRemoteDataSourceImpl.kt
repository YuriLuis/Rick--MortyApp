package com.yuri.apprickmorty.data.repositories.remote

import com.yuri.apprickmorty.data.api.RickMortyApi
import com.yuri.apprickmorty.data.models.PersonagemResponse
import retrofit2.Response

class PersonagemRemoteDataSourceImpl(
    private var api: RickMortyApi
) : PersonagemRemoteDataSource {
    override suspend fun getPersonagens(pagina: Int): Response<PersonagemResponse> {
        return api.getPersonagens(pagina)
    }

    override suspend fun getPersonagensPorNome(nome: String): Response<PersonagemResponse> {
        return api.getPersonagensPorNome(nome)
    }

    override suspend fun getPersonagensPorStatusEGenero(
        status: String,
        genero: String,
        pagina: Int
    ): Response<PersonagemResponse> {
        return api.getPersonagensPorStatusEGenero(status, genero, pagina)
    }

    override suspend fun getPersonagensPorGenero(
        genero: String,
        pagina: Int
    ): Response<PersonagemResponse> {
        return api.getPersonagensPorGenero(genero, pagina)
    }

    override suspend fun getPersonagensPorStatus(
        status: String,
        pagina: Int
    ): Response<PersonagemResponse> {
        return api.getPersonagensPorStatus(status, pagina)
    }
}