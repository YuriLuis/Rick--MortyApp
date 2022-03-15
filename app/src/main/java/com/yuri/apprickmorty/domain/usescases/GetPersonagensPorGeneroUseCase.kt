package com.yuri.apprickmorty.domain.usescases

import com.yuri.apprickmorty.data.models.PersonagemResponse
import com.yuri.apprickmorty.data.utils.Resource
import com.yuri.apprickmorty.domain.repository.PersonagemRepository

class GetPersonagensPorGeneroUseCase(
    private val repository: PersonagemRepository
) {
    suspend fun execute(genero: String, pagina: Int): Resource<PersonagemResponse>{
        return repository.getPersonagensPorGenero(genero,pagina)
    }
}