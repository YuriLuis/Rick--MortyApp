package com.yuri.apprickmorty.domain.usescases

import com.yuri.apprickmorty.data.models.PersonagemResponse
import com.yuri.apprickmorty.data.utils.Resource
import com.yuri.apprickmorty.domain.repository.PersonagemRepository

class GetPersonagensPorNomeUseCase(
    private val repository: PersonagemRepository
) {
    suspend fun execute(nome: String): Resource<PersonagemResponse>{
        return repository.getPersonagensPorNome(nome)
    }
}