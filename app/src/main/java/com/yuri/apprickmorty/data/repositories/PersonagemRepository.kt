package com.yuri.apprickmorty.data.repositories

import com.yuri.apprickmorty.data.models.PersonagemResponse
import com.yuri.apprickmorty.utils.Resource

interface PersonagemRepository {

    suspend fun getPersonagens(pagina: Int) : Resource<PersonagemResponse>
}