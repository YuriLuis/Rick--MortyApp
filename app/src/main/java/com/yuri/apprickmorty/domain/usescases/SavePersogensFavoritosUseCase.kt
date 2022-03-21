package com.yuri.apprickmorty.domain.usescases

import com.yuri.apprickmorty.data.models.Personagem
import com.yuri.apprickmorty.domain.repository.PersonagemRepository

class SavePersogensFavoritosUseCase(
    private val repository: PersonagemRepository
) {
    suspend fun execute(personagem: Personagem) = repository.savePersonagem(personagem)
}