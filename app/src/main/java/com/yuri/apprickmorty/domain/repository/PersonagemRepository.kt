package com.yuri.apprickmorty.domain.repository

import androidx.lifecycle.LiveData
import com.yuri.apprickmorty.data.models.Personagem
import com.yuri.apprickmorty.data.models.PersonagemResponse
import com.yuri.apprickmorty.data.utils.Resource

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

    suspend fun savePersonagem(personagem: Personagem)

    suspend fun deletePersonagem(personagem: Personagem)

    fun getAllPersonagensFavoritos(): LiveData<List<Personagem>>
}