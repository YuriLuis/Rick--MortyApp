package com.yuri.apprickmorty.domain.usescases

import androidx.lifecycle.LiveData
import com.yuri.apprickmorty.data.models.Personagem
import com.yuri.apprickmorty.domain.repository.PersonagemRepository

class GetAllPersonagensFavoritosUseCase(
    private val repository: PersonagemRepository
) {
    fun execute(): LiveData<List<Personagem>>{
        return repository.getAllPersonagensFavoritos()
    }
}