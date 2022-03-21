package com.yuri.apprickmorty.domain.repository

import androidx.lifecycle.LiveData
import com.yuri.apprickmorty.data.models.PersonagemResponse
import com.yuri.apprickmorty.data.api.RickMortyApi
import com.yuri.apprickmorty.data.models.Personagem
import com.yuri.apprickmorty.data.repositories.local.PersonagemLocalDataSource
import com.yuri.apprickmorty.data.repositories.remote.PersonagemRemoteDataSource
import com.yuri.apprickmorty.data.utils.Resource
import retrofit2.Response

class PersonagemRepositoryImpl(
    private val personagemRemoteDataSource: PersonagemRemoteDataSource,
    private val personagemLocalDataSource: PersonagemLocalDataSource
) : PersonagemRepository {

    private fun responseToResource(response: Response<PersonagemResponse>): Resource<PersonagemResponse> {
        if (response.isSuccessful) {
            response.body()?.let { result ->
                return Resource.Success(result)
            }
        }
        return Resource.Error(response.message())
    }


    override suspend fun getPersonagens(pagina: Int): Resource<PersonagemResponse> {
        return responseToResource(response = personagemRemoteDataSource.getPersonagens(pagina))
    }

    override suspend fun getPersonagensPorNome(nome: String): Resource<PersonagemResponse> {
        return responseToResource(response = personagemRemoteDataSource.getPersonagensPorNome(nome))
    }

    override suspend fun getPersonagensPorStatusEGenero(
        status: String,
        genero: String,
        pagina: Int
    ): Resource<PersonagemResponse> {
        return responseToResource(
            response = personagemRemoteDataSource.getPersonagensPorStatusEGenero(
                status = status,
                genero = genero,
                pagina = pagina
            )
        )
    }

    override suspend fun getPersonagensPorGenero(
        genero: String,
        pagina: Int
    ): Resource<PersonagemResponse> {
        return responseToResource(
            response = personagemRemoteDataSource.getPersonagensPorGenero(
                genero = genero,
                pagina = pagina
            )
        )
    }

    override suspend fun getPersonagensPorStatus(
        status: String,
        pagina: Int
    ): Resource<PersonagemResponse> {
        return responseToResource(response = personagemRemoteDataSource.getPersonagensPorStatus(status, pagina))
    }

    override suspend fun savePersonagem(personagem: Personagem) {
        personagemLocalDataSource.savePersonagemRoomDatabase(personagem)
    }

    override suspend fun deletePersonagem(personagem: Personagem) {
        personagemLocalDataSource.deletePersonagemRoomDatabase(personagem)
    }

    override fun getAllPersonagensFavoritos(): LiveData<List<Personagem>> {
        return personagemLocalDataSource.getAllPersonagensRoomDatabase()
    }

}