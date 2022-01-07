package com.yuri.apprickmorty.data.repositories

import com.yuri.apprickmorty.data.models.LocacaoData
import com.yuri.apprickmorty.data.models.Personagem
import com.yuri.apprickmorty.data.models.PersonagemResponse
import com.yuri.apprickmorty.utils.Resource

class FakePersonagemRepository : PersonagemRepository {

    private var personagens = mutableListOf(
        Personagem(
            1, "nome01", "alive", "especie01",
            "male", LocacaoData("nome01", "url01"),
            LocacaoData("nome01", "url01"), "imagem01", listOf(
                "1", "2", "3"
            )
        ),
        Personagem(
            2, "nome02", "alive", "especie02",
            "male", LocacaoData("nome02", "url02"),
            LocacaoData("nome02", "url02"), "imagem02", listOf(
                "1", "2", "3"
            )
        ),
        Personagem(
            3, "nome03", "dead", "especie03",
            "female", LocacaoData("nome03", "url03"),
            LocacaoData("nome03", "url03"), "imagem03", listOf(
                "1", "2", "3"
            )
        ), Personagem(
            4, "nome01", "alive", "especie01",
            "female", LocacaoData("nome01", "url01"),
            LocacaoData("nome01", "url01"), "imagem01", listOf(
                "1", "2", "3"
            )
        ),
        Personagem(
            5, "nome02", "dead", "especie02",
            "male", LocacaoData("nome02", "url02"),
            LocacaoData("nome02", "url02"), "imagem02", listOf(
                "1", "2", "3"
            )
        ),
        Personagem(
            6, "nome03", "alive", "especie03",
            "female", LocacaoData("nome03", "url03"),
            LocacaoData("nome03", "url03"), "imagem03", listOf(
                "1", "2", "3"
            )
        ), Personagem(
            7, "nome01", "alive", "especie01",
            "male", LocacaoData("nome01", "url01"),
            LocacaoData("nome01", "url01"), "imagem01", listOf(
                "1", "2", "3"
            )
        ),
        Personagem(
            8, "nome02", "alive", "especie02",
            "female", LocacaoData("nome02", "url02"),
            LocacaoData("nome02", "url02"), "imagem02", listOf(
                "1", "2", "3"
            )
        ),
        Personagem(
            9, "nome03", "dead", "especie03",
            "male", LocacaoData("nome03", "url03"),
            LocacaoData("nome03", "url03"), "imagem03", listOf(
                "1", "2", "3"
            )
        ), Personagem(
            10, "nome01", "dead", "especie01",
            "male", LocacaoData("nome01", "url01"),
            LocacaoData("nome01", "url01"), "imagem01", listOf(
                "1", "2", "3"
            )
        ),
        Personagem(
            11, "nome02", "alive", "especie02",
            "male", LocacaoData("nome02", "url02"),
            LocacaoData("nome02", "url02"), "imagem02", listOf(
                "1", "2", "3"
            )
        ),
        Personagem(
            12, "nome03", "dead", "especie03",
            "female", LocacaoData("nome03", "url03"),
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

    override suspend fun getPersonagensPorNome(nome: String): Resource<PersonagemResponse> {
        val lista = mutableListOf<Personagem>()
        return if (shouldReturnNetworkError) {
            Resource.Error("Erro getPersonagens")
        } else {
            personagens.forEach { personagem ->
                if (personagem.nome.contains(nome)) {
                    lista.add(personagem)
                }
            }
            personagens.clear()
            personagens = lista
            Resource.Success(PersonagemResponse(personagens))
        }
    }

    override suspend fun getPersonagensPorStatusEGenero(
        status: String,
        genero: String,
        pagina: Int
    ): Resource<PersonagemResponse> {
        val lista = mutableListOf<Personagem>()
        return if (shouldReturnNetworkError) {
            Resource.Error("Erro getPersonagens")
        } else {
            personagens.forEach { personagem ->
                if (personagem.status == status &&
                    personagem.genero == genero
                ) {
                    lista.add(personagem)
                }
            }
            personagens.clear()
            personagens = lista
            Resource.Success(PersonagemResponse(personagens))
        }
    }

    override suspend fun getPersonagensPorGenero(
        genero: String,
        pagina: Int
    ): Resource<PersonagemResponse> {
        val lista = mutableListOf<Personagem>()
        return if (shouldReturnNetworkError) {
            Resource.Error("Erro getPersonagens")
        } else {
            personagens.forEach { personagem ->
                if (personagem.genero == genero) {
                    lista.add(personagem)
                }
            }
            personagens.clear()
            personagens = lista
            Resource.Success(PersonagemResponse(personagens))
        }
    }

    override suspend fun getPersonagensPorStatus(
        status: String,
        pagina: Int
    ): Resource<PersonagemResponse> {
        val lista = mutableListOf<Personagem>()
        return if (shouldReturnNetworkError) {
            Resource.Error("Erro getPersonagens")
        } else {
            personagens.forEach { personagem ->
                if (personagem.status == status) {
                    lista.add(personagem)
                }
            }
            personagens.clear()
            personagens = lista
            Resource.Success(PersonagemResponse(personagens))
        }
    }
}