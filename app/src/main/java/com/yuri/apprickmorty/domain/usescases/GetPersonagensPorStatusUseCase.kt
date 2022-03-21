package com.yuri.apprickmorty.domain.usescases

import com.yuri.apprickmorty.data.models.PersonagemResponse
import com.yuri.apprickmorty.data.utils.Resource
import com.yuri.apprickmorty.domain.repository.PersonagemRepository

class GetPersonagensPorStatusUseCase(
    private val repository: PersonagemRepository
) {
    suspend fun execute(status: String,pagina: Int): Resource<PersonagemResponse>{
        return repository.getPersonagensPorStatus(status,pagina)
    }
}