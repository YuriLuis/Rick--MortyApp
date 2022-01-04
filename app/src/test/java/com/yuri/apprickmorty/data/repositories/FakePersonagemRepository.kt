package com.yuri.apprickmorty.data.repositories

import androidx.lifecycle.MutableLiveData
import com.yuri.apprickmorty.data.models.LocacaoData
import com.yuri.apprickmorty.data.models.Personagem
import com.yuri.apprickmorty.data.models.PersonagemResponse
import com.yuri.apprickmorty.utils.Resource

class FakePersonagemRepository : PersonagemRepository {

    private val personagens = mutableListOf(
        Personagem(
            1, "nome01", "status01", "especie01",
            "genero01", LocacaoData("nome01", "url01"),
            LocacaoData("nome01", "url01"), "imagem01", listOf(
                "1", "2", "3"
            )
        ),
        Personagem(
            2, "nome02", "status02", "especie02",
            "genero02", LocacaoData("nome02", "url02"),
            LocacaoData("nome02", "url02"), "imagem02", listOf(
                "1", "2", "3"
            )
        ),
        Personagem(
            3, "nome03", "status03", "especie03",
            "genero03", LocacaoData("nome03", "url03"),
            LocacaoData("nome03", "url03"), "imagem03", listOf(
                "1", "2", "3"
            )
        )
    )

    private var shouldReturnNetworkError = false

    override suspend fun getPersonagens(pagina: Int): Resource<PersonagemResponse> {
        return if (shouldReturnNetworkError) {
            Resource.Error("Erro getPersonagens")
        } else {
            Resource.Success(PersonagemResponse(personagens))
        }
    }
}