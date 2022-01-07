package com.yuri.apprickmorty.data.repositories

import com.yuri.apprickmorty.data.models.PersonagemResponse
import com.yuri.apprickmorty.utils.Resource

interface PersonagemRepository {

    suspend fun getPersonagens(pagina: Int): Resource<PersonagemResponse>

    suspend fun getPersonagensPorNome(nome: String): Resource<PersonagemResponse>

    suspend fun getPersonagensPorStatusEGenero(
        status: String,
        genero: String,
        pagina: Int
    ): Resource<PersonagemResponse>

    suspend fun getPersonagensPorGenero(genero: String, pagina: Int): Resource<PersonagemResponse>

    suspend fun getPersonagensPorStatus(status: String,pagina: Int): Resource<PersonagemResponse>
}